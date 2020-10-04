package com.ae.visuavid.service.mapper;

import com.ae.visuavid.domain.AdminMediaEntity;
import com.ae.visuavid.service.dto.AdminMediaDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring", uses = {MediaSlideMapper.class, SlideItemMapper.class})
public interface AdminMediaMapper extends BaseMapper<AdminMediaDTO, AdminMediaEntity> {

    @Mapping(target = "slides", ignore = true)
    AdminMediaDTO toDtoBasic(AdminMediaEntity entity);

    default List<AdminMediaDTO> toDtosBasic(List<AdminMediaEntity> entityList) {
        List<AdminMediaDTO> dtos = new ArrayList<>();
        entityList.forEach(entity -> dtos.add(toDtoBasic(entity)));
        return dtos;
    }
}
