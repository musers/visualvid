package com.ae.visuavid.service.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import javax.validation.constraints.NotNull;

public class AdminMediaDTO implements BaseDTO {
    private UUID id;

    @NotNull
    private String name;

    private String description;

    @NotNull
    private String categoryId;

    @NotNull
    private BigDecimal indianPrice;

    @NotNull
    private BigDecimal usdPrice;

    private String previewVideoS3Url;

    private String previewVideoS3Key;

    private String thumbNailS3Url;

    private String thumbNailS3Key;

    private String mediaPlaceholder;

    private String textPlaceholder;

    private String turnAroundTime;

    private Integer templateCount;

    private List<MediaSlideDTO> slides;

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

    public String getTurnAroundTime() {
        return turnAroundTime;
    }

    public void setTurnAroundTime(String turnAroundTime) {
        this.turnAroundTime = turnAroundTime;
    }

    public List<MediaSlideDTO> getSlides() {
        return slides;
    }

    public Integer getTemplateCount() {
        return templateCount;
    }

    public void setTemplateCount(Integer templateCount) {
        this.templateCount = templateCount;
    }

    public void setSlides(List<MediaSlideDTO> slides) {
        this.slides = slides;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getPreviewVideoS3Key() {
        return previewVideoS3Key;
    }

    public void setPreviewVideoS3Key(String previewVideoS3Key) {
        this.previewVideoS3Key = previewVideoS3Key;
    }

    public String getThumbNailS3Key() {
        return thumbNailS3Key;
    }

    public void setThumbNailS3Key(String thumbNailS3Key) {
        this.thumbNailS3Key = thumbNailS3Key;
    }
}
