package com.ae.visuavid.service.dto;

public class ItemCustomizationDTO {
    private String adminMediaId;
    private String currencyCode;
    private boolean optedForAdvCustomization;
    private boolean optedForPremumDelivery;

    public String getAdminMediaId() {
        return adminMediaId;
    }

    public void setAdminMediaId(String adminMediaId) {
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
}
