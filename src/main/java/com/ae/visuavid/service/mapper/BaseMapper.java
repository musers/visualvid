package com.ae.visuavid.service.mapper;

import com.ae.visuavid.domain.BaseEntity;
import com.ae.visuavid.service.dto.BaseDTO;
import java.util.ArrayList;
import java.util.List;

public interface BaseMapper<T extends BaseDTO, E extends BaseEntity> {
    E toEntity(T dto);

    T toDto(E entity);

    default List<E> toEntities(List<T> dtoList) {
        List<E> entities = new ArrayList<>();
        dtoList.forEach(dto -> entities.add(toEntity(dto)));
        return entities;
    }

    default List<T> toDtos(List<E> entityList) {
        List<T> dtos = new ArrayList<>();
        entityList.forEach(entity -> dtos.add(toDto(entity)));
        return dtos;
    }
}