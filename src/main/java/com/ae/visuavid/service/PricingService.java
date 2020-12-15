package com.ae.visuavid.service;

import com.ae.visuavid.config.ApplicationProperties;
import com.ae.visuavid.constants.Currency;
import com.ae.visuavid.domain.AdminMediaEntity;
import com.ae.visuavid.service.dto.ItemCustomizationDTO;
import com.ae.visuavid.service.dto.PricingDTO;
import com.ae.visuavid.utils.NumberUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Service
public class PricingService {
    @Autowired
    private ApplicationProperties applicationProperties;

    @Autowired
    private NumberUtility numberUtility;

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

        BigDecimal baseAmount = numberUtility.add(basicAmount, discountAmount);
        BigDecimal couponDiscountAmount = computeAndGetCouponDiscountAmount(baseAmount, itemCustomization.getCouponCode());
        BigDecimal totalAmountWithoutGst = numberUtility.
            subtract(numberUtility.add(baseAmount, advancedCustomizationAmount, premiumDeliveryAmount), couponDiscountAmount);
        BigDecimal gstAmount = numberUtility.percentage(totalAmountWithoutGst, applicationProperties.getGst());
        BigDecimal totalAmount = numberUtility.add(totalAmountWithoutGst, gstAmount);

        pricing.setCurrencyCode(currency.name());
        pricing.setBasicAmount(basicAmount);
        pricing.setDiscountAmount(discountAmount);
        pricing.setAdvancedCustomizationAmount(advancedCustomizationAmount);
        pricing.setPremiumDeliveryAmount(premiumDeliveryAmount);
        pricing.setCouponDiscountAmount(couponDiscountAmount);
        pricing.setGstAmount(gstAmount);
        pricing.setTotalAmount(totalAmount);
        return pricing;
    }

    private BigDecimal computeAndGetCouponDiscountAmount(BigDecimal baseAmount, String couponCode) {
        //TODO need to get value from database
        return null;
    }
}
