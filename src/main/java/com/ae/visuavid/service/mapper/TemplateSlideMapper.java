package com.ae.visuavid.service.mapper;

import com.ae.visuavid.domain.TemplateSlideEntity;
import com.ae.visuavid.domain.TemplateSlideItemEntity;
import com.ae.visuavid.service.dto.TemplateSlideDTO;
import com.ae.visuavid.service.dto.TemplateSlideItemDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = { TemplateSlideItemMapper.class })
public interface TemplateSlideMapper extends BaseMapper<TemplateSlideDTO, TemplateSlideEntity> {}
