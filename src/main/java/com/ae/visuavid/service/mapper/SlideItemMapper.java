package com.ae.visuavid.service.mapper;

import com.ae.visuavid.domain.SlideItemEntity;
import com.ae.visuavid.service.dto.OrderSlideItemDTO;
import com.ae.visuavid.service.dto.SlideItemDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface SlideItemMapper extends BaseMapper<SlideItemDTO, SlideItemEntity> {

    @Mappings({
        @Mapping(target = "itemType", source = "type"),
        @Mapping(target = "itemLabel", source = "label"),
        @Mapping(target = "itemOrder", source = "order"),
        @Mapping(target = "adminSlideItemId", source = "id"),
        @Mapping( target = "id", ignore = true)
    })
    OrderSlideItemDTO toOrderSlideDto(SlideItemEntity slideItemEntity);
}
