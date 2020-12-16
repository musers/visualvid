package com.ae.visuavid.repository;

import com.ae.visuavid.domain.CouponInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CouponInfoRepository extends JpaRepository<CouponInfoEntity, UUID> {
    CouponInfoEntity findByCouponCodeAndActiveTrue(String couponCode);
}
