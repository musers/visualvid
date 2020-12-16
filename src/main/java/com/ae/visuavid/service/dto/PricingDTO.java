package com.ae.visuavid.service.dto;

import java.math.BigDecimal;

public class PricingDTO {
    private String currencyCode;
    private BigDecimal basicAmount;
    private BigDecimal discountAmount;
    private BigDecimal advancedCustomizationAmount;
    private BigDecimal premiumDeliveryAmount;
    private BigDecimal gstAmount;
    private BigDecimal totalAmount;
    private BigDecimal couponDiscountAmount;
    private Integer couponDiscountPercentage;
    private Integer gstPercentage;
    private String couponCode;

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public BigDecimal getBasicAmount() {
        return basicAmount;
    }

    public void setBasicAmount(BigDecimal basicAmount) {
        this.basicAmount = basicAmount;
    }

    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }

    public BigDecimal getAdvancedCustomizationAmount() {
        return advancedCustomizationAmount;
    }

    public void setAdvancedCustomizationAmount(BigDecimal advancedCustomizationAmount) {
        this.advancedCustomizationAmount = advancedCustomizationAmount;
    }

    public BigDecimal getPremiumDeliveryAmount() {
        return premiumDeliveryAmount;
    }

    public void setPremiumDeliveryAmount(BigDecimal premiumDeliveryAmount) {
        this.premiumDeliveryAmount = premiumDeliveryAmount;
    }

    public BigDecimal getGstAmount() {
        return gstAmount;
    }

    public void setGstAmount(BigDecimal gstAmount) {
        this.gstAmount = gstAmount;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getCouponDiscountAmount() {
        return couponDiscountAmount;
    }

    public void setCouponDiscountAmount(BigDecimal couponDiscountAmount) {
        this.couponDiscountAmount = couponDiscountAmount;
    }

    public Integer getCouponDiscountPercentage() {
        return couponDiscountPercentage;
    }

    public void setCouponDiscountPercentage(Integer couponDiscountPercentage) {
        this.couponDiscountPercentage = couponDiscountPercentage;
    }

    public Integer getGstPercentage() {
        return gstPercentage;
    }

    public void setGstPercentage(Integer gstPercentage) {
        this.gstPercentage = gstPercentage;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }
}

