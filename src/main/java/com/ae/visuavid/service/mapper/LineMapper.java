package com.ae.visuavid.service.mapper;

import com.ae.visuavid.domain.LineEntity;
import com.ae.visuavid.service.dto.LineDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LineMapper extends BaseMapper<LineDTO, LineEntity> {}
