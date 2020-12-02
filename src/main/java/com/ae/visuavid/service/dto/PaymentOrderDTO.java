package com.ae.visuavid.service.dto;

import java.math.BigDecimal;

public class PaymentOrderDTO {
    private BigDecimal amount;
    private String orderIdRef;
    private String razorPayOrderId;
    private String currencyCode;

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getOrderIdRef() {
        return orderIdRef;
    }

    public void setOrderIdRef(String orderIdRef) {
        this.orderIdRef = orderIdRef;
    }

    public String getRazorPayOrderId() {
        return razorPayOrderId;
    }

    public void setRazorPayOrderId(String razorPayOrderId) {
        this.razorPayOrderId = razorPayOrderId;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }
}
