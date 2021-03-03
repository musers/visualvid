package com.ae.visuavid.service.dto;

import com.ae.visuavid.domain.UserSubscriptionEntity;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public class UserSubscriptionDTO implements BaseDTO {
    private UUID id;
    private BigDecimal price;
    private BigDecimal discountAmount;
    private BigDecimal amountPaid;
    private Integer orderCount;
    private Integer availedOrders;
    private Instant startDate;
    private Instant endDate;
    private UUID userId;
    private UUID subscriptionId;
    private String currencyCode;
    private String categoryId;
    private String userName;
    private String status;
    private String planName;

    public UserSubscriptionDTO() {}

    public UserSubscriptionDTO(UserSubscriptionEntity userSubscription) {
        this.id = userSubscription.getId();
        this.price = userSubscription.getBasicAmount();
        this.discountAmount = userSubscription.getDiscountAmount();
        this.amountPaid = userSubscription.getTotalAmount();
        this.orderCount = userSubscription.getOrderCount();
        this.availedOrders = userSubscription.getAvailedOrders();
        this.startDate = userSubscription.getStartDate();
        this.endDate = userSubscription.getEndDate();
        this.userId = userSubscription.getUser().getId();
        this.subscriptionId = userSubscription.getSubscription().getId();
        this.currencyCode = userSubscription.getCurrencyCode();
        this.categoryId = userSubscription.getCategoryId();
        this.userName = userSubscription.getUserName();
        this.status = userSubscription.getStatus();
        this.planName = userSubscription.getPlanName();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }

    public BigDecimal getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(BigDecimal amountPaid) {
        this.amountPaid = amountPaid;
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

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public UUID getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(UUID subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(Integer orderCount) {
        this.orderCount = orderCount;
    }

    public Integer getAvailedOrders() {
        return availedOrders;
    }

    public void setAvailedOrders(Integer availedOrders) {
        this.availedOrders = availedOrders;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }
}
