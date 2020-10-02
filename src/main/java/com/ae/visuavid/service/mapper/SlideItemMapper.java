package com.ae.visuavid.service.mapper;

import com.ae.visuavid.domain.SlideItemEntity;
import com.ae.visuavid.service.dto.SlideItemDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SlideItemMapper extends BaseMapper<SlideItemDTO, SlideItemEntity> {}
