package com.ae.visuavid.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import com.ae.visuavid.config.ApplicationProperties;
import com.ae.visuavid.constants.Currency;
import com.ae.visuavid.domain.AdminMediaEntity;
import com.ae.visuavid.domain.CouponInfoEntity;
import com.ae.visuavid.service.dto.ItemCustomizationDTO;
import com.ae.visuavid.service.dto.PricingDTO;
import com.ae.visuavid.utils.NumberUtility;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

@ExtendWith(MockitoExtension.class)
public class PricingServiceTest {

    @Mock
    private CouponInfoService couponInfoService;

    private PricingService pricingService;

    @BeforeEach
    public void setUp() {
        pricingService = new PricingService();

        ApplicationProperties applicationProperties = new ApplicationProperties();
        applicationProperties.setGst(18);

        ReflectionTestUtils.setField(pricingService, "applicationProperties", applicationProperties);
        ReflectionTestUtils.setField(pricingService, "numberUtility", new NumberUtility());
        ReflectionTestUtils.setField(pricingService, "couponInfoService", couponInfoService);
    }

    @Test
    public void shouldApplyPercentageCouponToPrice() {
        AdminMediaEntity adminMediaEntity = buildAdminMediaEntity();
        ItemCustomizationDTO itemCustomizationDTO = buildItemCustomization();

        CouponInfoEntity couponInfoEntity = new CouponInfoEntity();
        couponInfoEntity.setCouponCode("SAVE10");
        couponInfoEntity.setCouponDiscountPercentage(10);
        couponInfoEntity.setActive(true);
        couponInfoEntity.setStartDate(Instant.now().minus(1, ChronoUnit.DAYS));
        couponInfoEntity.setEndDate(Instant.now().plus(1, ChronoUnit.DAYS));

        when(couponInfoService.getValidCoupon("SAVE10")).thenReturn(couponInfoEntity);

        PricingDTO pricingDTO = pricingService.computePrice(adminMediaEntity, itemCustomizationDTO);

        assertThat(pricingDTO.getCouponDiscountAmount()).isEqualByComparingTo(new BigDecimal("20.00"));
        assertThat(pricingDTO.getTotalAmount()).isEqualByComparingTo(new BigDecimal("212.40"));
    }

    @Test
    public void shouldCapFixedCouponToSubtotal() {
        AdminMediaEntity adminMediaEntity = buildAdminMediaEntity();
        adminMediaEntity.setIndianAdvCustomizationPrice(BigDecimal.ZERO);
        adminMediaEntity.setIndianPremumDeliveryPrice(BigDecimal.ZERO);

        ItemCustomizationDTO itemCustomizationDTO = buildItemCustomization();
        itemCustomizationDTO.setOptedForAdvCustomization(false);
        itemCustomizationDTO.setOptedForPremumDelivery(false);

        CouponInfoEntity couponInfoEntity = new CouponInfoEntity();
        couponInfoEntity.setCouponCode("FREEALL");
        couponInfoEntity.setCouponDiscountAmount(new BigDecimal("999.00"));
        couponInfoEntity.setActive(true);
        couponInfoEntity.setStartDate(Instant.now().minus(1, ChronoUnit.DAYS));
        couponInfoEntity.setEndDate(Instant.now().plus(1, ChronoUnit.DAYS));

        when(couponInfoService.getValidCoupon("SAVE10")).thenReturn(couponInfoEntity);

        PricingDTO pricingDTO = pricingService.computePrice(adminMediaEntity, itemCustomizationDTO);

        assertThat(pricingDTO.getCouponDiscountAmount()).isEqualByComparingTo(new BigDecimal("200.00"));
        assertThat(pricingDTO.getTotalAmount()).isEqualByComparingTo(BigDecimal.ZERO);
    }

    private AdminMediaEntity buildAdminMediaEntity() {
        AdminMediaEntity adminMediaEntity = new AdminMediaEntity();
        adminMediaEntity.setId(UUID.randomUUID());
        adminMediaEntity.setIndianPrice(new BigDecimal("250.00"));
        adminMediaEntity.setIndianDiscPrice(new BigDecimal("50.00"));
        adminMediaEntity.setIndianAdvCustomizationPrice(new BigDecimal("20.00"));
        adminMediaEntity.setIndianPremumDeliveryPrice(new BigDecimal("10.00"));
        adminMediaEntity.setUsdPrice(new BigDecimal("20.00"));
        adminMediaEntity.setUsdDiscPrice(BigDecimal.ZERO);
        adminMediaEntity.setUsdAdvCustomizationPrice(BigDecimal.ZERO);
        adminMediaEntity.setUsdPremumDeliveryPrice(BigDecimal.ZERO);
        return adminMediaEntity;
    }

    private ItemCustomizationDTO buildItemCustomization() {
        ItemCustomizationDTO itemCustomizationDTO = new ItemCustomizationDTO();
        itemCustomizationDTO.setAdminMediaId(UUID.randomUUID());
        itemCustomizationDTO.setCurrencyCode(Currency.INR.name());
        itemCustomizationDTO.setCouponCode("SAVE10");
        itemCustomizationDTO.setOptedForAdvCustomization(true);
        itemCustomizationDTO.setOptedForPremumDelivery(true);
        return itemCustomizationDTO;
    }
}
