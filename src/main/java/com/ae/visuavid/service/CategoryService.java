package com.ae.visuavid.service;

import com.ae.visuavid.repository.CategoryRepository;
import com.ae.visuavid.service.dto.CategoryDTO;
import com.ae.visuavid.service.mapper.CategoryMapper;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CategoryService {
    @Autowired
    CategoryMapper categoryMapper;

    @Autowired
    CategoryRepository categoryRepository;

    public List<CategoryDTO> findCategories() {
        log.info("Getting categories");
        return categoryMapper.toDtos(categoryRepository.findAll());
    }

    public CategoryDTO save(CategoryDTO dto) {
        log.info("create/update categories");
        categoryRepository.save(categoryMapper.toEntity(dto));
        return dto;
    }
}
