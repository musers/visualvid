package com.ae.visuavid.service.mapper;

import com.ae.visuavid.domain.CouponInfoEntity;
import com.ae.visuavid.service.dto.CouponInfoDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CouponInfoMapper extends BaseMapper<CouponInfoDTO, CouponInfoEntity> {
}
