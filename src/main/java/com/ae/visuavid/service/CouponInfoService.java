package com.ae.visuavid.service;

import com.ae.visuavid.domain.CouponInfoEntity;
import com.ae.visuavid.repository.CouponInfoRepository;
import com.ae.visuavid.service.dto.CouponInfoDTO;
import com.ae.visuavid.service.mapper.CouponInfoMapper;
import com.ae.visuavid.web.rest.errors.ApiRuntimeException;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
@Transactional
public class CouponInfoService {

    @Autowired
    private CouponInfoRepository couponInfoRepository;

    @Autowired
    private CouponInfoMapper couponInfoMapper;

    @Transactional(readOnly = true)
    public List<CouponInfoDTO> findAll() {
        return couponInfoMapper.toDtos(couponInfoRepository.findAll());
    }

    @Transactional(readOnly = true)
    public CouponInfoDTO findOne(UUID id) {
        Optional<CouponInfoEntity> couponInfoEntity = couponInfoRepository.findById(id);
        if (couponInfoEntity.isPresent()) {
            return couponInfoMapper.toDto(couponInfoEntity.get());
        }
        throw new ApiRuntimeException("Coupon not found");
    }

    public CouponInfoDTO create(CouponInfoDTO couponInfoDTO) {
        if (couponInfoDTO.getId() != null) {
            throw new ApiRuntimeException("New coupon cannot already have an id");
        }
        CouponInfoEntity entity = prepareForSave(couponInfoDTO);
        return couponInfoMapper.toDto(couponInfoRepository.save(entity));
    }

    public CouponInfoDTO update(CouponInfoDTO couponInfoDTO) {
        if (couponInfoDTO.getId() == null) {
            throw new ApiRuntimeException("Coupon id is required for update");
        }
        if (!couponInfoRepository.existsById(couponInfoDTO.getId())) {
            throw new ApiRuntimeException("Coupon not found");
        }
        CouponInfoEntity entity = prepareForSave(couponInfoDTO);
        return couponInfoMapper.toDto(couponInfoRepository.save(entity));
    }

    @Transactional(readOnly = true)
    public CouponInfoDTO validateCoupon(String couponCode) {
        return couponInfoMapper.toDto(getValidCoupon(couponCode));
    }

    @Transactional(readOnly = true)
    public CouponInfoEntity getValidCoupon(String couponCode) {
        String normalizedCouponCode = normalizeCouponCode(couponCode);
        if (!StringUtils.hasText(normalizedCouponCode)) {
            throw new ApiRuntimeException("Coupon code is required");
        }

        CouponInfoEntity couponInfoEntity = couponInfoRepository.findByCouponCodeIgnoreCase(normalizedCouponCode);
        if (couponInfoEntity == null) {
            throw new ApiRuntimeException("Coupon code is invalid");
        }
        if (!couponInfoEntity.isActive()) {
            throw new ApiRuntimeException("Coupon code is inactive");
        }

        Instant now = Instant.now();
        if (couponInfoEntity.getStartDate() != null && now.isBefore(couponInfoEntity.getStartDate())) {
            throw new ApiRuntimeException("Coupon code is not active yet");
        }
        if (couponInfoEntity.getEndDate() != null && now.isAfter(couponInfoEntity.getEndDate())) {
            throw new ApiRuntimeException("Coupon code has expired");
        }
        return couponInfoEntity;
    }

    public String normalizeCouponCode(String couponCode) {
        return couponCode == null ? null : couponCode.trim().toUpperCase();
    }

    private CouponInfoEntity prepareForSave(CouponInfoDTO couponInfoDTO) {
        String normalizedCouponCode = normalizeCouponCode(couponInfoDTO.getCouponCode());
        if (!StringUtils.hasText(normalizedCouponCode)) {
            throw new ApiRuntimeException("Coupon code is required");
        }
        validateCouponDates(couponInfoDTO);
        validateCouponDiscount(couponInfoDTO);
        assertCouponCodeIsUnique(normalizedCouponCode, couponInfoDTO.getId());

        couponInfoDTO.setCouponCode(normalizedCouponCode);
        return couponInfoMapper.toEntity(couponInfoDTO);
    }

    private void validateCouponDates(CouponInfoDTO couponInfoDTO) {
        if (couponInfoDTO.getStartDate() != null && couponInfoDTO.getEndDate() != null) {
            if (couponInfoDTO.getEndDate().isBefore(couponInfoDTO.getStartDate())) {
                throw new ApiRuntimeException("Coupon end date must be after start date");
            }
        }
    }

    private void validateCouponDiscount(CouponInfoDTO couponInfoDTO) {
        boolean hasAmountDiscount = couponInfoDTO.getCouponDiscountAmount() != null && couponInfoDTO.getCouponDiscountAmount().signum() > 0;
        boolean hasPercentageDiscount =
            couponInfoDTO.getCouponDiscountPercentage() != null && couponInfoDTO.getCouponDiscountPercentage() > 0;

        if (!hasAmountDiscount && !hasPercentageDiscount) {
            throw new ApiRuntimeException("Coupon must have a discount amount or percentage");
        }
        if (hasPercentageDiscount && couponInfoDTO.getCouponDiscountPercentage() > 100) {
            throw new ApiRuntimeException("Coupon discount percentage cannot be more than 100");
        }
    }

    private void assertCouponCodeIsUnique(String couponCode, UUID currentCouponId) {
        CouponInfoEntity existingCoupon = couponInfoRepository.findByCouponCodeIgnoreCase(couponCode);
        if (existingCoupon != null) {
            if (currentCouponId == null || !existingCoupon.getId().equals(currentCouponId)) {
                throw new ApiRuntimeException("Coupon code already exists");
            }
        }
    }
}
