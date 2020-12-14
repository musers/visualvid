package com.ae.visuavid.service;

import com.ae.visuavid.repository.CategoryRepository;
import com.ae.visuavid.service.dto.CategoryDTO;
import com.ae.visuavid.service.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private CategoryRepository categoryRepository;

    public List<CategoryDTO> findCategories() {
//        log.info("Getting categories");
        return categoryMapper.toDtos(categoryRepository.findAll());
    }

    public CategoryDTO save(CategoryDTO dto) {
//        log.info("create/update categories");
        categoryRepository.save(categoryMapper.toEntity(dto));
        return dto;
    }

    public String getName(String categoryId) {
        //TODO need to get name
        return null;
    }
}
