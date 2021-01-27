package com.ae.visuavid.service;

import com.ae.visuavid.domain.AdminMediaEntity;
import com.ae.visuavid.domain.CategoryEntity;
import com.ae.visuavid.domain.SubCategoryEntity;
import com.ae.visuavid.repository.AdminUploadFormRepository;
import com.ae.visuavid.repository.CategoryRepository;
import com.ae.visuavid.repository.SubCategoryRepository;
import com.ae.visuavid.service.dto.CategoryDTO;
import com.ae.visuavid.service.dto.SubCategoryDTO;
import com.ae.visuavid.service.mapper.CategoryMapper;
import com.ae.visuavid.service.mapper.SubCategoryMapper;
import com.ae.visuavid.web.rest.errors.ApiRuntimeException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
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

    public List<CategoryDTO> findCategories() {
        //        log.info("Getting categories");
        return categoryMapper.toDtos(categoryRepository.findAll());
    }

    public CategoryDTO save(CategoryDTO dto) {
        categoryRepository.save(categoryMapper.toEntity(dto));
        return dto;
    }

    public String getName(String categoryId) {
        CategoryEntity category = categoryRepository.findByCategoryId(categoryId);
        if (category != null) {
            category.getCategoryName();
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
        subCategory.setSubCategoryName(dto.getSubCategoryName());
        subCategoryRepository.save(subCategory);
        return dto;
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
}
