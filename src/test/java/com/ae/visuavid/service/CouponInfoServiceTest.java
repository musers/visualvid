package com.ae.visuavid.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

import com.ae.visuavid.domain.CouponInfoEntity;
import com.ae.visuavid.repository.CouponInfoRepository;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

@ExtendWith(MockitoExtension.class)
public class CouponInfoServiceTest {

    @Mock
    private CouponInfoRepository couponInfoRepository;

    private CouponInfoService couponInfoService;

    @BeforeEach
    public void setUp() {
        couponInfoService = new CouponInfoService();
        ReflectionTestUtils.setField(couponInfoService, "couponInfoRepository", couponInfoRepository);
    }

    @Test
    public void shouldReturnValidCoupon() {
        CouponInfoEntity couponInfoEntity = new CouponInfoEntity();
        couponInfoEntity.setCouponCode("SAVE20");
        couponInfoEntity.setActive(true);
        couponInfoEntity.setStartDate(Instant.now().minus(1, ChronoUnit.DAYS));
        couponInfoEntity.setEndDate(Instant.now().plus(1, ChronoUnit.DAYS));

        when(couponInfoRepository.findByCouponCodeIgnoreCase("SAVE20")).thenReturn(couponInfoEntity);

        CouponInfoEntity result = couponInfoService.getValidCoupon(" save20 ");

        assertThat(result.getCouponCode()).isEqualTo("SAVE20");
    }

    @Test
    public void shouldRejectExpiredCoupon() {
        CouponInfoEntity couponInfoEntity = new CouponInfoEntity();
        couponInfoEntity.setCouponCode("SAVE20");
        couponInfoEntity.setActive(true);
        couponInfoEntity.setStartDate(Instant.now().minus(3, ChronoUnit.DAYS));
        couponInfoEntity.setEndDate(Instant.now().minus(1, ChronoUnit.DAYS));

        when(couponInfoRepository.findByCouponCodeIgnoreCase("SAVE20")).thenReturn(couponInfoEntity);

        assertThatThrownBy(() -> couponInfoService.getValidCoupon("SAVE20")).hasMessageContaining("expired");
    }
}
