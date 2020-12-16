package com.ae.visuavid.service.dto;

import javax.validation.constraints.NotNull;
import java.util.UUID;

public class ItemCustomizationDTO {
    @NotNull
    private UUID adminMediaId;

    @NotNull
    private String currencyCode;

    private String couponCode;
    private boolean optedForAdvCustomization;
    private boolean optedForPremumDelivery;

    public UUID getAdminMediaId() {
        return adminMediaId;
    }

    public void setAdminMediaId(UUID adminMediaId) {
        this.adminMediaId = adminMediaId;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public boolean isOptedForAdvCustomization() {
        return optedForAdvCustomization;
    }

    public void setOptedForAdvCustomization(boolean optedForAdvCustomization) {
        this.optedForAdvCustomization = optedForAdvCustomization;
    }

    public boolean isOptedForPremumDelivery() {
        return optedForPremumDelivery;
    }

    public void setOptedForPremumDelivery(boolean optedForPremumDelivery) {
        this.optedForPremumDelivery = optedForPremumDelivery;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }
}
