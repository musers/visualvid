package com.ae.visuavid.service.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import org.springframework.cloud.cloudfoundry.com.fasterxml.jackson.annotation.JsonIgnore;

public class SubscriptionDTO implements BaseDTO {
    private UUID id;
    private String name;
    private String type;
    private BigDecimal monthlyPriceLocal;
    private BigDecimal yearlyPriceLocal;
    private BigDecimal monthlyPriceUsd;
    private BigDecimal yearlyPriceUsd;
    private BigDecimal discountAmount;
    private BigDecimal discountPercentage;
    private Integer downloads;
    private Integer downloadPerDay;
    private Boolean unLimitedDownloadsEnable;
    private String textLine01;
    private String textLine02;
    private String textLine03;
    private String textLine04;
    private String status;

    @JsonIgnore
    private List<CategoryDTO> categories;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getMonthlyPriceLocal() {
        return monthlyPriceLocal;
    }

    public void setMonthlyPriceLocal(BigDecimal monthlyPriceLocal) {
        this.monthlyPriceLocal = monthlyPriceLocal;
    }

    public BigDecimal getYearlyPriceLocal() {
        return yearlyPriceLocal;
    }

    public void setYearlyPriceLocal(BigDecimal yearlyPriceLocal) {
        this.yearlyPriceLocal = yearlyPriceLocal;
    }

    public BigDecimal getMonthlyPriceUsd() {
        return monthlyPriceUsd;
    }

    public void setMonthlyPriceUsd(BigDecimal monthlyPriceUsd) {
        this.monthlyPriceUsd = monthlyPriceUsd;
    }

    public BigDecimal getYearlyPriceUsd() {
        return yearlyPriceUsd;
    }

    public void setYearlyPriceUsd(BigDecimal yearlyPriceUsd) {
        this.yearlyPriceUsd = yearlyPriceUsd;
    }

    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }

    public BigDecimal getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(BigDecimal discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public Integer getDownloads() {
        return downloads;
    }

    public void setDownloads(Integer downloads) {
        this.downloads = downloads;
    }

    public Integer getDownloadPerDay() {
        return downloadPerDay;
    }

    public void setDownloadPerDay(Integer downloadPerDay) {
        this.downloadPerDay = downloadPerDay;
    }

    public Boolean getUnLimitedDownloadsEnable() {
        return unLimitedDownloadsEnable;
    }

    public void setUnLimitedDownloadsEnable(Boolean unLimitedDownloadsEnable) {
        this.unLimitedDownloadsEnable = unLimitedDownloadsEnable;
    }

    public String getTextLine01() {
        return textLine01;
    }

    public void setTextLine01(String textLine01) {
        this.textLine01 = textLine01;
    }

    public String getTextLine02() {
        return textLine02;
    }

    public void setTextLine02(String textLine02) {
        this.textLine02 = textLine02;
    }

    public String getTextLine03() {
        return textLine03;
    }

    public void setTextLine03(String textLine03) {
        this.textLine03 = textLine03;
    }

    public String getTextLine04() {
        return textLine04;
    }

    public void setTextLine04(String textLine04) {
        this.textLine04 = textLine04;
    }

    public List<CategoryDTO> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryDTO> categories) {
        this.categories = categories;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
