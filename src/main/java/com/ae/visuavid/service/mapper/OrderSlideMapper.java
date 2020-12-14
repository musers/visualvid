package com.ae.visuavid.service.mapper;

import com.ae.visuavid.domain.OrderSlideEntity;
import com.ae.visuavid.service.dto.OrderSlideDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = { OrderSlideItemMapper.class })
public interface OrderSlideMapper extends BaseMapper<OrderSlideDTO, OrderSlideEntity> {}
