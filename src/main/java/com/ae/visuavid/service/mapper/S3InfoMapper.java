package com.ae.visuavid.service.mapper;

import com.ae.visuavid.domain.S3InfoEntity;
import com.ae.visuavid.service.dto.S3InfoDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface S3InfoMapper extends BaseMapper<S3InfoDTO, S3InfoEntity> {}
