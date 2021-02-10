package com.ae.visuavid.service.mapper;

import com.ae.visuavid.domain.OrderSlideItemEntity;
import com.ae.visuavid.service.dto.OrderSlideItemDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderSlideItemMapper extends BaseMapper<OrderSlideItemDTO, OrderSlideItemEntity> {
}
