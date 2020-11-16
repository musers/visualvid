package com.ae.visuavid.service.mapper;

import com.ae.visuavid.domain.TemplateEntity;
import com.ae.visuavid.domain.TemplateSlideEntity;
import com.ae.visuavid.service.dto.TemplateDTO;
import com.ae.visuavid.service.dto.TemplateSlideDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = { TemplateSlideMapper.class })
public interface TemplateMapper extends BaseMapper<TemplateDTO, TemplateEntity> {}
