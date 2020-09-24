package com.ae.visuavid.service.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ae.visuavid.domain.SlideItemEntity;
import com.ae.visuavid.service.dto.SlideItemDTO;

@Component
public class SlideItemMapper implements EntityMapper<SlideItemDTO, SlideItemEntity>{

	@Override
	public SlideItemEntity toEntity(SlideItemDTO dto) {
		SlideItemEntity entity = new SlideItemEntity();
		entity.setId(dto.getId());
		entity.setLabel(dto.getLabel());
		entity.setType(dto.getType());
		entity.setOrder(dto.getOrder());
		return entity;
	}

	@Override
	public SlideItemDTO toDto(SlideItemEntity entity) {
		SlideItemDTO dto = new SlideItemDTO();
		dto.setId(entity.getId());
		dto.setLabel(entity.getLabel());
		dto.setType(entity.getType());
		dto.setOrder(entity.getOrder());
		return dto;
	}

	@Override
	public List<SlideItemEntity> toEntity(List<SlideItemDTO> dtoList) {
		List<SlideItemEntity> entities = new ArrayList<SlideItemEntity>();
		dtoList.forEach(d -> {
			entities.add(toEntity(d));
		});
		return entities;
	}

	@Override
	public List<SlideItemDTO> toDto(List<SlideItemEntity> entityList) {
		List<SlideItemDTO> dtos = new ArrayList<SlideItemDTO>();
		entityList.forEach(e -> {
			dtos.add(toDto(e));
		});
		return dtos;
	}

}
