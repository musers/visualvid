package com.ae.visuavid.service;

import com.ae.visuavid.config.ApplicationProperties;
import com.ae.visuavid.constants.Currency;
import com.ae.visuavid.domain.AdminMediaEntity;
import com.ae.visuavid.domain.CouponInfoEntity;
import com.ae.visuavid.service.dto.ItemCustomizationDTO;
import com.ae.visuavid.service.dto.PricingDTO;
import com.ae.visuavid.utils.NumberUtility;
import java.math.BigDecimal;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class PricingService {
    @Autowired
    private ApplicationProperties applicationProperties;

    @Autowired
    private NumberUtility numberUtility;

    @Autowired
    private CouponInfoService couponInfoService;

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
        BigDecimal totalAmountBeforeTax = numberUtility.add(baseAmount, advancedCustomizationAmount, premiumDeliveryAmount);
        CouponInfoEntity couponInfoEntity = resolveCoupon(itemCustomization.getCouponCode());
        Integer couponDiscountPercentage = couponInfoEntity == null ? 0 : couponInfoEntity.getCouponDiscountPercentage();
        BigDecimal couponDiscountAmount = computeCouponDiscountAmount(baseAmount, totalAmountBeforeTax, couponInfoEntity);
        BigDecimal totalAmountWithoutGst = numberUtility.subtract(totalAmountBeforeTax, couponDiscountAmount);
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
        pricing.setCouponCode(couponInfoEntity == null ? null : couponInfoEntity.getCouponCode());
        return pricing;
    }

    private CouponInfoEntity resolveCoupon(String couponCode) {
        if (StringUtils.hasText(couponCode)) {
            return couponInfoService.getValidCoupon(couponCode);
        }
        return null;
    }

    private BigDecimal computeCouponDiscountAmount(
        BigDecimal baseAmount,
        BigDecimal totalAmountBeforeTax,
        CouponInfoEntity couponInfoEntity
    ) {
        if (couponInfoEntity == null) {
            return BigDecimal.ZERO;
        }

        BigDecimal discountAmount = couponInfoEntity.getCouponDiscountAmount();
        if (discountAmount != null && discountAmount.signum() > 0) {
            if (totalAmountBeforeTax == null || totalAmountBeforeTax.signum() <= 0) {
                return BigDecimal.ZERO;
            }
            return discountAmount.min(totalAmountBeforeTax);
        }

        Integer discountPercentage = couponInfoEntity.getCouponDiscountPercentage();
        if (discountPercentage != null && discountPercentage > 0) {
            return numberUtility.percentage(baseAmount, discountPercentage);
        }
        return BigDecimal.ZERO;
    }
}
