package com.ae.visuavid.service.mapper;

import com.ae.visuavid.domain.OrderEntity;
import com.ae.visuavid.service.dto.OrderDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = { LineMapper.class })
public interface OrderMapper extends BaseMapper<OrderDTO, OrderEntity> {}
