package com.ae.visuavid.service.mapper;

import com.ae.visuavid.domain.TemplateSlideItemEntity;
import com.ae.visuavid.service.dto.TemplateSlideItemDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TemplateSlideItemMapper extends BaseMapper<TemplateSlideItemDTO, TemplateSlideItemEntity> {}
