package com.ae.visuavid.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "media")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class AdminMediaEntity extends AbstractAuditingEntity implements BaseEntity {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id")
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "category_id")
    private String categoryId;

    @Column(name = "description")
    private String description;

    @Column(name = "indian_price")
    private BigDecimal indianPrice;

    @Column(name = "indian_disc_price")
    private BigDecimal indianDiscPrice;

    @Column(name = "indian_adv_cust_price")
    private BigDecimal indianAdvCustomizationPrice;

    @Column(name = "indian_prem_delivery_price")
    private BigDecimal indianPremumDeliveryPrice;

    @Column(name = "usd_disc_price")
    private BigDecimal usdDiscPrice;

    @Column(name = "usd_adv_cust_price")
    private BigDecimal usdAdvCustomizationPrice;

    @Column(name = "usd_prem_delivery_price")
    private BigDecimal usdPremumDeliveryPrice;

    @Column(name = "usd_price")
    private BigDecimal usdPrice;

    @Column(name = "preview_video_s3_url")
    private String previewVideoS3Url;

    @Column(name = "thumb_nail_s3_url")
    private String thumbNailS3Url;

    @Column(name = "media_placeholders")
    private String mediaPlaceholder;

    @Column(name = "text_placeholders")
    private String textPlaceholder;

    @Column(name = "turn_around_time")
    private Long turnAroundTime;

    @Column(name = "thumb_nail_s3_key")
    private String thumbNailS3Key;

    @Column(name = "preview_video_s3_key")
    private String previewVideoS3Key;

    @Column(name = "template_count")
    private Integer templateCount;

    @Column(name = "earnings")
    private Double earnings;

    @Column(name = "tags")
    private String tags;

    @OneToMany(mappedBy = "media", fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private List<MediaSlideEntity> slides;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public BigDecimal getIndianPrice() {
        return indianPrice;
    }

    public void setIndianPrice(BigDecimal indianPrice) {
        this.indianPrice = indianPrice;
    }

    public BigDecimal getUsdPrice() {
        return usdPrice;
    }

    public void setUsdPrice(BigDecimal usdPrice) {
        this.usdPrice = usdPrice;
    }

    public String getPreviewVideoS3Url() {
        return previewVideoS3Url;
    }

    public void setPreviewVideoS3Url(String previewVideoS3Url) {
        this.previewVideoS3Url = previewVideoS3Url;
    }

    public String getThumbNailS3Url() {
        return thumbNailS3Url;
    }

    public void setThumbNailS3Url(String thumbNailS3Url) {
        this.thumbNailS3Url = thumbNailS3Url;
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

    public Long getTurnAroundTime() {
        return turnAroundTime;
    }

    public void setTurnAroundTime(Long turnAroundTime) {
        this.turnAroundTime = turnAroundTime;
    }

    public List<MediaSlideEntity> getSlides() {
        return slides;
    }

    public void setSlides(List<MediaSlideEntity> slides) {
        this.slides = slides;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getThumbNailS3Key() {
        return thumbNailS3Key;
    }

    public void setThumbNailS3Key(String thumbNailS3Key) {
        this.thumbNailS3Key = thumbNailS3Key;
    }

    public String getPreviewVideoS3Key() {
        return previewVideoS3Key;
    }

    public void setPreviewVideoS3Key(String previewVideoS3Key) {
        this.previewVideoS3Key = previewVideoS3Key;
    }

    public Integer getTemplateCount() {
        return templateCount;
    }

    public void setTemplateCount(Integer templateCount) {
        this.templateCount = templateCount;
    }

    public Double getEarnings() {
        return earnings;
    }

    public void setEarnings(Double earnings) {
        this.earnings = earnings;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public BigDecimal getIndianDiscPrice() {
        return indianDiscPrice;
    }

    public void setIndianDiscPrice(BigDecimal indianDiscPrice) {
        this.indianDiscPrice = indianDiscPrice;
    }

    public BigDecimal getIndianAdvCustomizationPrice() {
        return indianAdvCustomizationPrice;
    }

    public void setIndianAdvCustomizationPrice(BigDecimal indianAdvCustomizationPrice) {
        this.indianAdvCustomizationPrice = indianAdvCustomizationPrice;
    }

    public BigDecimal getIndianPremumDeliveryPrice() {
        return indianPremumDeliveryPrice;
    }

    public void setIndianPremumDeliveryPrice(BigDecimal indianPremumDeliveryPrice) {
        this.indianPremumDeliveryPrice = indianPremumDeliveryPrice;
    }

    public BigDecimal getUsdDiscPrice() {
        return usdDiscPrice;
    }

    public void setUsdDiscPrice(BigDecimal usdDiscPrice) {
        this.usdDiscPrice = usdDiscPrice;
    }

    public BigDecimal getUsdAdvCustomizationPrice() {
        return usdAdvCustomizationPrice;
    }

    public void setUsdAdvCustomizationPrice(BigDecimal usdAdvCustomizationPrice) {
        this.usdAdvCustomizationPrice = usdAdvCustomizationPrice;
    }

    public BigDecimal getUsdPremumDeliveryPrice() {
        return usdPremumDeliveryPrice;
    }

    public void setUsdPremumDeliveryPrice(BigDecimal usdPremumDeliveryPrice) {
        this.usdPremumDeliveryPrice = usdPremumDeliveryPrice;
    }
}
