package com.ae.visuavid.service.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class OrderDTO implements BaseDTO {
    private UUID id;
    private String orderId;
    private String salesId;
    private String orderStatus;
    private UUID adminMediaId;
    private String assignedUserId;
    private String assignedUserName;
    private String name;
    private String description;
    private String categoryId;
    private String categoryName;
    private String previewVideoS3Url;
    private String previewVideoS3Key;
    private String thumbNailS3Url;
    private String thumbNailS3Key;
    private String mediaPlaceholder;
    private String textPlaceholder;
    private long turnAroundTime;
    private String tags;
    private String couponCode;
    // Pricing info
    private String razorPayOrderId;
    private String razorPayPaymentId;
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
    private List<OrderSlideDTO> orderSlides;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getSalesId() {
        return salesId;
    }

    public void setSalesId(String salesId) {
        this.salesId = salesId;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public UUID getAdminMediaId() {
        return adminMediaId;
    }

    public void setAdminMediaId(UUID adminMediaId) {
        this.adminMediaId = adminMediaId;
    }

    public String getAssignedUserId() {
        return assignedUserId;
    }

    public void setAssignedUserId(String assignedUserId) {
        this.assignedUserId = assignedUserId;
    }

    public String getAssignedUserName() {
        return assignedUserName;
    }

    public void setAssignedUserName(String assignedUserName) {
        this.assignedUserName = assignedUserName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getPreviewVideoS3Url() {
        return previewVideoS3Url;
    }

    public void setPreviewVideoS3Url(String previewVideoS3Url) {
        this.previewVideoS3Url = previewVideoS3Url;
    }

    public String getPreviewVideoS3Key() {
        return previewVideoS3Key;
    }

    public void setPreviewVideoS3Key(String previewVideoS3Key) {
        this.previewVideoS3Key = previewVideoS3Key;
    }

    public String getThumbNailS3Url() {
        return thumbNailS3Url;
    }

    public void setThumbNailS3Url(String thumbNailS3Url) {
        this.thumbNailS3Url = thumbNailS3Url;
    }

    public String getThumbNailS3Key() {
        return thumbNailS3Key;
    }

    public void setThumbNailS3Key(String thumbNailS3Key) {
        this.thumbNailS3Key = thumbNailS3Key;
    }

    public String getMediaPlaceholder() {
        return mediaPlaceholder;
    }

    public void setMediaPlaceholder(String mediaPlaceholder) {
        this.mediaPlaceholder = mediaPlaceholder;
    }

    public String getTextPlaceholder() {
        return textPlaceholder;
    }

    public void setTextPlaceholder(String textPlaceholder) {
        this.textPlaceholder = textPlaceholder;
    }

    public long getTurnAroundTime() {
        return turnAroundTime;
    }

    public void setTurnAroundTime(long turnAroundTime) {
        this.turnAroundTime = turnAroundTime;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getRazorPayOrderId() {
        return razorPayOrderId;
    }

    public void setRazorPayOrderId(String razorPayOrderId) {
        this.razorPayOrderId = razorPayOrderId;
    }

    public String getRazorPayPaymentId() {
        return razorPayPaymentId;
    }

    public void setRazorPayPaymentId(String razorPayPaymentId) {
        this.razorPayPaymentId = razorPayPaymentId;
    }

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

    public List<OrderSlideDTO> getOrderSlides() {
        return orderSlides;
    }

    public void setOrderSlides(List<OrderSlideDTO> orderSlides) {
        this.orderSlides = orderSlides;
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
