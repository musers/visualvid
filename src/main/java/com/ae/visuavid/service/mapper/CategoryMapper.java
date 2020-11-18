package com.ae.visuavid.service.mapper;

import com.ae.visuavid.domain.CategoryEntity;
import com.ae.visuavid.service.dto.CategoryDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper extends BaseMapper<CategoryDTO, CategoryEntity> {}
