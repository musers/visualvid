package com.ae.visuavid.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class AdminMediaDto {
    @JsonProperty("id")
    private UUID id;

    @JsonProperty("name")
    @NotNull
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("category")
    @NotNull
    private String category;

    @JsonProperty("indianPrice")
    @NotNull
    private BigDecimal indianPrice;

    @JsonProperty("usdPrice")
    @NotNull
    private BigDecimal usdPrice;

    @JsonProperty("previewVideoS3Url")
    private String previewVideoS3Url;

    @JsonProperty("thumbNailS3Url")
    private String thumbNailS3Url;

    @JsonProperty("mediaPlaceholder")
    private String mediaPlaceholder;

    @JsonProperty("textPlaceholder")
    private String textPlaceholder;

    @JsonProperty("turnAroundTime")
    private String turnAroundTime;

    @JsonProperty("s3InfoId")
    private UUID s3InfoId;

    @JsonProperty("slides")
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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

    public UUID getS3InfoId() {
        return s3InfoId;
    }

    public void setS3InfoId(UUID s3InfoId) {
        this.s3InfoId = s3InfoId;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setSlides(List<MediaSlideDTO> slides) {
        this.slides = slides;
    }
}
