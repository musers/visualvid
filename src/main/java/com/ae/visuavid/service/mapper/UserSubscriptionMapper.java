package com.ae.visuavid.service.mapper;

import com.ae.visuavid.domain.UserSubscriptionEntity;
import com.ae.visuavid.service.dto.UserSubscriptionDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = { SubscriptionMapper.class })
public interface UserSubscriptionMapper extends BaseMapper<UserSubscriptionDTO, UserSubscriptionEntity> {}
