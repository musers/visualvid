package com.ae.visuavid.service.mapper;

import com.ae.visuavid.domain.MediaSlideEntity;
import com.ae.visuavid.service.dto.MediaSlideDTO;
import com.ae.visuavid.service.dto.OrderSlideDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = {SlideItemMapper.class})
public interface MediaSlideMapper extends BaseMapper<MediaSlideDTO, MediaSlideEntity> {

    @Mappings({
        @Mapping(target = "orderSlideItems", source = "slideItems"),
        @Mapping(target = "adminSlideId", source = "id"),
        @Mapping(target = "id", ignore = true)
    })
    OrderSlideDTO toOrderSlideDto(MediaSlideEntity mediaSlideEntity);
}
