package com.ae.visuavid.service.mapper;

import com.ae.visuavid.domain.MediaSlideEntity;
import com.ae.visuavid.service.dto.MediaSlideDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = { SlideItemMapper.class })
public interface MediaSlideMapper extends BaseMapper<MediaSlideDTO, MediaSlideEntity> {}
