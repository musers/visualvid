package com.ae.visuavid.service.mapper;

import com.ae.visuavid.domain.CategoryEntity;
import com.ae.visuavid.domain.SubCategoryEntity;
import com.ae.visuavid.service.dto.CategoryDTO;
import com.ae.visuavid.service.dto.SubCategoryDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SubCategoryMapper extends BaseMapper<SubCategoryDTO, SubCategoryEntity> {}
