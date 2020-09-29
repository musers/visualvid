package com.ae.visuavid.service.mapper;

import com.ae.visuavid.domain.AdminMediaEntity;
import com.ae.visuavid.service.dto.AdminMediaDTO;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AdminMediaMapper implements EntityMapper<AdminMediaDTO, AdminMediaEntity> {
    @Autowired
    MediaSlideMapper slideMapper;

    @Override
    public AdminMediaEntity toEntity(AdminMediaDTO dto) {
        AdminMediaEntity entity = new AdminMediaEntity();
        entity.setId(dto.getId());
        entity.setCategoryId(dto.getCategoryId());
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
    public AdminMediaDTO toDto(AdminMediaEntity entity) {
        AdminMediaDTO dto = new AdminMediaDTO();
        dto.setId(entity.getId());
        dto.setCategoryId(entity.getCategoryId());
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
    public List<AdminMediaEntity> toEntity(List<AdminMediaDTO> dtoList) {
        List<AdminMediaEntity> entities = new ArrayList<>();
        dtoList.forEach(dto -> entities.add(toEntity(dto)));
        return entities;
    }

    @Override
    public List<AdminMediaDTO> toDto(List<AdminMediaEntity> entityList) {
        List<AdminMediaDTO> dtos = new ArrayList<>();
        entityList.forEach(
            entity -> {
                dtos.add(toDto(entity));
            }
        );
        return dtos;
    }
}
