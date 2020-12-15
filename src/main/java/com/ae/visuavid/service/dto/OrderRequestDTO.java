package com.ae.visuavid.service.dto;

import java.util.List;

public class OrderRequestDTO {
    private String couponCode;
    private String currencyCode;
    private List<ItemCustomizationDTO> itemCustomizations;

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    public List<ItemCustomizationDTO> getItemCustomizations() {
        return itemCustomizations;
    }

    public void setItemCustomizations(List<ItemCustomizationDTO> itemCustomizations) {
        this.itemCustomizations = itemCustomizations;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }
}
