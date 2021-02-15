package com.ae.visuavid.service;

import com.ae.visuavid.client.S3Service;
import com.ae.visuavid.domain.AdminMediaEntity;
import com.ae.visuavid.domain.CategoryEntity;
import com.ae.visuavid.domain.SubCategoryEntity;
import com.ae.visuavid.enumeration.S3MediaStatusType;
import com.ae.visuavid.repository.AdminUploadFormRepository;
import com.ae.visuavid.repository.CategoryRepository;
import com.ae.visuavid.repository.SubCategoryRepository;
import com.ae.visuavid.service.dto.CategoryDTO;
import com.ae.visuavid.service.dto.SubCategoryDTO;
import com.ae.visuavid.service.mapper.CategoryMapper;
import com.ae.visuavid.service.mapper.SubCategoryMapper;
import com.ae.visuavid.web.rest.errors.ApiRuntimeException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

@Service
@Transactional
public class CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private SubCategoryRepository subCategoryRepository;

    @Autowired
    private SubCategoryMapper subCategoryMapper;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private AdminUploadFormRepository adminUploadFormRepository;

    @Autowired
    private S3Service s3Service;

    public List<CategoryDTO> findCategories(boolean includeSubCategories) {
        List<CategoryEntity> categoryEntities = categoryRepository.findAll();
        if (includeSubCategories) {
            if (categoryEntities != null) {
                for (CategoryEntity cEntity : categoryEntities) {
                    cEntity.getSubCategories().size();
                }
            }
        }
        return categoryMapper.toDtos(categoryEntities);
    }

    public CategoryDTO save(CategoryDTO dto) {
        CategoryEntity entityCreated = categoryRepository.save(categoryMapper.toEntity(dto));
        List<String> s3KeyList = new ArrayList<>();
        s3KeyList.add(dto.getS3CoverImageKey());
        s3Service.updateS3InfoStatus(s3KeyList, S3MediaStatusType.COMPLETED.name());
        return categoryMapper.toDto(entityCreated);
    }

    public String getCategoryName(String id) {
        if (!StringUtils.isEmpty(id)) {
            CategoryEntity category = categoryRepository.findById(UUID.fromString(id)).get();
            if (category != null) {
                return category.getName();
            }
        }
        return null;
    }

    public String getSubCategoryName(String subCategoryId) {
        if (!StringUtils.isEmpty(subCategoryId)) {
            Optional<SubCategoryEntity> subCategory = subCategoryRepository.findById(UUID.fromString(subCategoryId));
            if (subCategory.isPresent()) {
                return subCategory.get().getName();
            }
        }
        return null;
    }

    public CategoryDTO findCategoryById(UUID categoryUUID) {
        Optional<CategoryEntity> categoryOptional = categoryRepository.findById(categoryUUID);
        if (categoryOptional.isPresent()) {
            return categoryMapper.toDto(categoryOptional.get());
        }
        return null;
    }

    private CategoryEntity findByCategoryId(UUID categoryUUID) {
        Optional<CategoryEntity> categoryOptional = categoryRepository.findById(categoryUUID);
        if (categoryOptional.isPresent()) {
            return categoryOptional.get();
        }
        return null;
    }

    public SubCategoryDTO saveSubCategory(SubCategoryDTO dto) {
        CategoryEntity category = findByCategoryId(UUID.fromString(dto.getCategoryId()));
        SubCategoryEntity subCategory = subCategoryMapper.toEntity(dto);
        subCategory.setCategory(category);
        subCategory.setName(dto.getName());
        SubCategoryEntity se = subCategoryRepository.save(subCategory);
        return subCategoryMapper.toDto(se);
    }

    public void deleteCategory(String categoryUUID) {
        List<AdminMediaEntity> medias = adminUploadFormRepository.findByCategoryId(categoryUUID);
        if (!CollectionUtils.isEmpty(medias)) {
            throw new ApiRuntimeException("Category cannot be deleted");
        }
        CategoryEntity category = findByCategoryId(UUID.fromString(categoryUUID));
        List<SubCategoryEntity> subCategories = subCategoryRepository.findByCategory(category);
        if (!CollectionUtils.isEmpty(subCategories)) {
            throw new ApiRuntimeException("Category cannot be deleted");
        }
        categoryRepository.delete(category);
    }

    public void deleteSubCategory(String subCategoryId) {
        List<AdminMediaEntity> medias = adminUploadFormRepository.findBySubCategoryId(subCategoryId);
        if (!CollectionUtils.isEmpty(medias)) {
            throw new ApiRuntimeException("sub-category cannot be deleted");
        }
        Optional<SubCategoryEntity> subCategoryEntity = subCategoryRepository.findById(UUID.fromString(subCategoryId));
        if (subCategoryEntity.isPresent()) {
            subCategoryRepository.delete(subCategoryEntity.get());
        }
    }

    public List<SubCategoryDTO> findSubCategoriesByCategoryId(String categoryId) {
        CategoryEntity category = findByCategoryId(UUID.fromString(categoryId));
        return subCategoryMapper.toDtos(subCategoryRepository.findByCategory(category));
    }

    public List<SubCategoryDTO> getAllSubCategories() {
        List<SubCategoryDTO> subCategoryDtos = new ArrayList<>();
        List<CategoryEntity> categories = categoryRepository.findAll();
        if (categories != null) {
            for (CategoryEntity cEntity : categories) {
                List<SubCategoryEntity> subCategoryEntities = cEntity.getSubCategories();
                if (subCategoryEntities != null && subCategoryEntities.size() > 0) {
                    List<SubCategoryDTO> sDtos = subCategoryMapper.toDtos(subCategoryEntities);
                    sDtos.forEach(
                        sDto -> {
                            sDto.setCategoryName(cEntity.getName());
                            sDto.setCategoryId(cEntity.getId().toString());
                        }
                    );
                    subCategoryDtos.addAll(sDtos);
                }
            }
        }
        return subCategoryDtos;
    }

    public void rename(String type, String id, @NotNull String newName) {
        if ("category".equalsIgnoreCase(type)) {
            List<CategoryEntity> categories = categoryRepository.findByNameIgnoreCase(newName);
            if (categories != null && categories.size() > 0) {
                Optional<CategoryEntity> existingCategory = categories
                    .stream()
                    .filter(c -> !id.equalsIgnoreCase(c.getId().toString()))
                    .findFirst();
                if (existingCategory.isPresent()) {
                    throw new ApiRuntimeException("Category already exists with the same name " + newName);
                }
            }
            CategoryEntity category = categoryRepository.findById(UUID.fromString(id)).get();
            category.setName(newName);
            categoryRepository.save(category);
        } else if ("subCategory".equalsIgnoreCase(type)) {
            SubCategoryEntity subCategory = subCategoryRepository.findById(UUID.fromString(id)).get();
            subCategory.setName(newName);
            //TODO need to check if already a sub category exists with the same name under this category. Currently UI is validating this
            subCategoryRepository.save(subCategory);
        }
    }
}
