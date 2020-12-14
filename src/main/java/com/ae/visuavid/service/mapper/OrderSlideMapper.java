package com.ae.visuavid.service.mapper;

import com.ae.visuavid.domain.MediaSlideEntity;
import com.ae.visuavid.domain.OrderSlideEntity;
import com.ae.visuavid.service.dto.OrderSlideDTO;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring", uses = {OrderSlideItemMapper.class})
public abstract class OrderSlideMapper implements BaseMapper<OrderSlideDTO, OrderSlideEntity> {

    @Autowired
    private MediaSlideMapper mediaSlideMapper;

    public List<OrderSlideDTO> toDTOsFromAdminMediaEntities(List<MediaSlideEntity> mediaSlideEntities) {
        List<OrderSlideDTO> list = new ArrayList<>();
        for (MediaSlideEntity mediaSlideEntity : mediaSlideEntities) {
            list.add(mediaSlideMapper.toOrderSlideDto(mediaSlideEntity));
        }
        return list;
    }
}
