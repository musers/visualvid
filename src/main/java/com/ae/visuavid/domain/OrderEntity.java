package com.ae.visuavid.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "vvid_order")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class OrderEntity extends AbstractAuditingEntity implements BaseEntity {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id")
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "order_id")
    private String orderId;

    @Column(name = "sales_id")
    private String salesId;

    @Column(name = "order_status")
    private String orderStatus;

    @Column(name = "admin_media_id")
    private UUID adminMediaId;

    @Column(name = "assigned_user_id")
    private String assignedUserId;

    @Column(name = "assigned_user_name")
    private String assignedUserName;

    @Column(name = "category_id")
    private String categoryId;

    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "preview_video_s3_url")
    private String previewVideoS3Url;

    @Column(name = "thumb_nail_s3_url")
    private String thumbNailS3Url;

    @Column(name = "thumb_nail_s3_key")
    private String thumbNailS3Key;

    @Column(name = "preview_video_s3_key")
    private String previewVideoS3Key;

    @Column(name = "media_placeholders")
    private String mediaPlaceholder;

    @Column(name = "text_placeholders")
    private String textPlaceholder;

    @Column(name = "turn_around_time")
    private long turnAroundTime;

    @Column(name = "tags")
    private String tags;

    @Column(name = "coupon_code")
    private String couponCode;

    // Pricing info
    @Column(name = "razor_pay_order_id")
    private String razorPayOrderId;

    @Column(name = "razor_pay_payment_id")
    private String razorPayPaymentId;

    @Column(name = "currency_code")
    private String currencyCode;

    @Column(name = "basic_amount")
    private BigDecimal basicAmount;

    @Column(name = "discount_amount")
    private BigDecimal discountAmount;

    @Column(name = "adv_customization_amount")
    private BigDecimal advancedCustomizationAmount;

    @Column(name = "premium_delivery_amount")
    private BigDecimal premiumDeliveryAmount;

    @Column(name = "gst_amount")
    private BigDecimal gstAmount;

    @Column(name = "total_amount")
    private BigDecimal totalAmount;

    @Column(name = "coupon_discount_amount")
    private BigDecimal couponDiscountAmount;

    @Column(name = "coupon_discount_percentage")
    private Integer couponDiscountPercentage;

    @Column(name = "gst_percentage")
    private Integer gstPercentage;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private List<OrderSlideEntity> orderSlides;

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

    public List<OrderSlideEntity> getOrderSlides() {
        return orderSlides;
    }

    public void setOrderSlides(List<OrderSlideEntity> orderSlides) {
        this.orderSlides = orderSlides;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
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
}
