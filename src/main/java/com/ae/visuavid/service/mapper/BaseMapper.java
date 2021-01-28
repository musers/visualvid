package com.ae.visuavid.service.mapper;

import com.ae.visuavid.domain.BaseEntity;
import com.ae.visuavid.service.dto.BaseDTO;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

public interface BaseMapper<T extends BaseDTO, E extends BaseEntity> {
    E toEntity(T dto);

    T toDto(E entity);

    default List<E> toEntities(List<T> dtoList) {
        List<E> entities = new ArrayList<>();
        if (dtoList != null) {
            dtoList.forEach(dto -> entities.add(toEntity(dto)));
        }
        return entities;
    }

    default List<T> toDtos(List<E> entityList) {
        List<T> dtos = new ArrayList<>();
        if (entityList != null) {
            entityList.forEach(entity -> dtos.add(toDto(entity)));
        }
        return dtos;
    }

    default List<T> toDtos(Page<E> entityList) {
        List<T> dtos = new ArrayList<>();
        if (entityList != null) {
            entityList.forEach(entity -> dtos.add(toDto(entity)));
        }
        return dtos;
    }
}
