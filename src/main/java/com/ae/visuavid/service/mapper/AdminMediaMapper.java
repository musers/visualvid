package com.ae.visuavid.service.mapper;

import com.ae.visuavid.domain.AdminMediaEntity;
import com.ae.visuavid.service.dto.AdminMediaDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = { MediaSlideMapper.class, SlideItemMapper.class })
public interface AdminMediaMapper extends BaseMapper<AdminMediaDTO, AdminMediaEntity> {}
