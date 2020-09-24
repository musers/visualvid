package com.ae.visuavid.service.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ae.visuavid.domain.MediaEntity;
import com.ae.visuavid.service.dto.AdminMediaDto;


@Component
public class AdminMediaMapper implements EntityMapper<AdminMediaDto, MediaEntity>{
	
	@Autowired MediaSlideMapper slideMapper;

	@Override
	public MediaEntity toEntity(AdminMediaDto dto) {
		MediaEntity entity = new MediaEntity();
		entity.setId(dto.getId());
		entity.setCategory(dto.getCategory());
		entity.setName(dto.getName());
		entity.setDescription(dto.getDescription());
		entity.setPreviewVideoS3Url(dto.getPreviewVideoS3Url());
		entity.setThumbNailS3Url(dto.getThumbNailS3Url());
		entity.setIndianPrice(dto.getIndianPrice());
		entity.setUsdPrice(dto.getUsdPrice());
		entity.setMediaPlaceholder(dto.getMediaPlaceholder());
		entity.setTextPlaceholder(dto.getTextPlaceholder());
		entity.setTurnAroundTime(dto.getTurnAroundTime());
		entity.setSlides(slideMapper.toEntity(dto.getSlides()));
		entity.getSlides().forEach(s -> s.setMedia(entity)); // bi-directional
		return entity;
	}

	@Override
	public AdminMediaDto toDto(MediaEntity entity) {
		AdminMediaDto dto  = new AdminMediaDto();
		dto.setId(entity.getId());
		dto.setCategory(entity.getCategory());
		dto.setName(entity.getName());
		dto.setDescription(entity.getDescription());
		dto.setPreviewVideoS3Url(entity.getPreviewVideoS3Url());
		dto.setThumbNailS3Url(entity.getThumbNailS3Url());
		dto.setIndianPrice(entity.getIndianPrice());
		dto.setUsdPrice(entity.getUsdPrice());
		dto.setMediaPlaceholder(entity.getMediaPlaceholder());
		dto.setTextPlaceholder(entity.getTextPlaceholder());
		dto.setTurnAroundTime(entity.getTurnAroundTime());
		dto.setSlides(slideMapper.toDto(entity.getSlides()));
		return dto;
	}

	@Override
	public List<MediaEntity> toEntity(List<AdminMediaDto> dtoList) {
		List<MediaEntity> entities = new ArrayList<>();
		dtoList.forEach(dto -> {
			entities.add(toEntity(dto));
		});
		return entities;
	}

	@Override
	public List<AdminMediaDto> toDto(List<MediaEntity> entityList) {
		List<AdminMediaDto> dtos = new ArrayList<>();
		entityList.forEach(entity -> {
			dtos.add(toDto(entity));
		});
		return dtos;
	}

}
