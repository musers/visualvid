package com.ae.visuavid.service.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ae.visuavid.domain.MediaSlideEntity;
import com.ae.visuavid.service.dto.MediaSlideDTO;

@Component
public class MediaSlideMapper implements EntityMapper<MediaSlideDTO, MediaSlideEntity>{
	
	@Autowired SlideItemMapper itemMapper;

	@Override
	public MediaSlideEntity toEntity(MediaSlideDTO dto) {
		MediaSlideEntity entity = new MediaSlideEntity();
		entity.setId( dto.getId());
		entity.setScreenShotS3Url(dto.getScreenShotS3Url());
		entity.setMediaSlides(itemMapper.toEntity(dto.getSlideItems()));
		entity.getMediaSlides().forEach(si -> si.setMediaSlide(entity)); // bi-directional
		return entity;
	}

	@Override
	public MediaSlideDTO toDto(MediaSlideEntity entity) {
		MediaSlideDTO dto = new MediaSlideDTO();
		dto.setId( entity.getId());
		dto.setScreenShotS3Url(entity.getScreenShotS3Url());
		dto.setSlideItems(itemMapper.toDto(entity.getMediaSlides()));
		return dto;
	}

	@Override
	public List<MediaSlideEntity> toEntity(List<MediaSlideDTO> dtoList) {
		List<MediaSlideEntity>  entities = new ArrayList<MediaSlideEntity>();
		dtoList.forEach(d -> {
			entities.add(toEntity(d));
		});
		return entities;
	}

	@Override
	public List<MediaSlideDTO> toDto(List<MediaSlideEntity> entityList) {
		List<MediaSlideDTO>  dtos = new ArrayList<MediaSlideDTO>();
		entityList.forEach(e -> {
			dtos.add(toDto(e));
		});
		return dtos;
	}

	



}
