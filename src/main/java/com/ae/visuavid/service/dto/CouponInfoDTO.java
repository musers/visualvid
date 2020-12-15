package com.ae.visuavid.service.dto;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public class CouponInfoDTO implements BaseDTO {
    private UUID id;
    private Instant startDate;
    private Instant endDate;
    private String description;
    private boolean active = true;
    private BigDecimal couponDiscountAmount;
    private Integer couponDiscountPercentage;
    private String couponCode;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Instant getStartDate() {
        return startDate;
    }

    public void setStartDate(Instant startDate) {
        this.startDate = startDate;
    }

    public Instant getEndDate() {
        return endDate;
    }

    public void setEndDate(Instant endDate) {
        this.endDate = endDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
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

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }
}
