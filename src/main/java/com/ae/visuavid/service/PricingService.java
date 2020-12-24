package com.ae.visuavid.service;

import com.ae.visuavid.config.ApplicationProperties;
import com.ae.visuavid.constants.Currency;
import com.ae.visuavid.domain.AdminMediaEntity;
import com.ae.visuavid.domain.CouponInfoEntity;
import com.ae.visuavid.repository.CouponInfoRepository;
import com.ae.visuavid.service.dto.ItemCustomizationDTO;
import com.ae.visuavid.service.dto.PricingDTO;
import com.ae.visuavid.utils.NumberUtility;
import java.math.BigDecimal;
import java.time.Instant;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PricingService {
    @Autowired
    private ApplicationProperties applicationProperties;

    @Autowired
    private NumberUtility numberUtility;

    @Autowired
    private CouponInfoRepository couponInfoRepository;

    public PricingDTO computePrice(@NotNull AdminMediaEntity adminMediaEntity, ItemCustomizationDTO itemCustomization) {
        PricingDTO pricing = new PricingDTO();

        Currency currency = Currency.valueOf(itemCustomization.getCurrencyCode().toUpperCase());
        BigDecimal basicAmount = null;
        BigDecimal discountAmount = null;
        BigDecimal advancedCustomizationAmount = null;
        BigDecimal premiumDeliveryAmount = null;
        if (currency.equals(Currency.USD)) {
            basicAmount = adminMediaEntity.getUsdPrice();
            discountAmount = adminMediaEntity.getUsdDiscPrice();
            advancedCustomizationAmount = adminMediaEntity.getUsdAdvCustomizationPrice();
            premiumDeliveryAmount = adminMediaEntity.getUsdPremumDeliveryPrice();
        } else if (currency.equals(Currency.INR)) {
            basicAmount = adminMediaEntity.getIndianPrice();
            discountAmount = adminMediaEntity.getIndianDiscPrice();
            advancedCustomizationAmount = adminMediaEntity.getIndianAdvCustomizationPrice();
            premiumDeliveryAmount = adminMediaEntity.getIndianPremumDeliveryPrice();
        }
        if (!itemCustomization.isOptedForAdvCustomization()) {
            advancedCustomizationAmount = BigDecimal.ZERO;
        }
        if (!itemCustomization.isOptedForPremumDelivery()) {
            premiumDeliveryAmount = BigDecimal.ZERO;
        }

        BigDecimal baseAmount = numberUtility.subtract(basicAmount, discountAmount);
        Integer couponDiscountPercentage = computeAndGetCouponDiscountPercentage(itemCustomization.getCouponCode());
        BigDecimal couponDiscountAmount = numberUtility.percentage(baseAmount, couponDiscountPercentage);
        BigDecimal totalAmountWithoutGst = numberUtility.subtract(
            numberUtility.add(baseAmount, advancedCustomizationAmount, premiumDeliveryAmount),
            couponDiscountAmount
        );
        BigDecimal gstAmount = numberUtility.percentage(totalAmountWithoutGst, applicationProperties.getGst());
        BigDecimal totalAmount = numberUtility.add(totalAmountWithoutGst, gstAmount);

        pricing.setCurrencyCode(currency.name());
        pricing.setBasicAmount(basicAmount);
        pricing.setDiscountAmount(discountAmount);
        pricing.setAdvancedCustomizationAmount(advancedCustomizationAmount);
        pricing.setPremiumDeliveryAmount(premiumDeliveryAmount);
        pricing.setCouponDiscountPercentage(couponDiscountPercentage);
        pricing.setCouponDiscountAmount(couponDiscountAmount);
        pricing.setGstAmount(gstAmount);
        pricing.setGstPercentage(applicationProperties.getGst());
        pricing.setTotalAmount(totalAmount);
        pricing.setCouponCode(itemCustomization.getCouponCode());
        return pricing;
    }

    private Integer computeAndGetCouponDiscountPercentage(String couponCode) {
        CouponInfoEntity couponInfoEntity = couponInfoRepository.findByCouponCodeAndActiveTrue(couponCode);
        if (couponInfoEntity != null) {
            Instant now = Instant.now();
            if (now.isAfter(couponInfoEntity.getStartDate()) && now.isBefore(couponInfoEntity.getEndDate())) {
                return couponInfoEntity.getCouponDiscountPercentage();
            }
        }
        return 0;
    }
}
