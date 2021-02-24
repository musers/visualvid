package com.ae.visuavid.service.mapper;

import com.ae.visuavid.domain.SubscriptionEntity;
import com.ae.visuavid.service.dto.SubscriptionDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", uses = { CategoryMapper.class }, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SubscriptionMapper extends BaseMapper<SubscriptionDTO, SubscriptionEntity> {}
