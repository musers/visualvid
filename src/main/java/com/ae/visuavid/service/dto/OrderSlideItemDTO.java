package com.ae.visuavid.service.dto;

import java.util.UUID;

public class OrderSlideItemDTO implements BaseDTO {
    private UUID id;
    private String itemType;
    private String itemLabel;
    private String itemValue;
    private Integer itemOrder;
    private String s3Url;
    private String s3Key;
    private UUID adminSlideItemId;

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getItemLabel() {
        return itemLabel;
    }

    public void setItemLabel(String itemLabel) {
        this.itemLabel = itemLabel;
    }

    public String getItemValue() {
        return itemValue;
    }

    public void setItemValue(String itemValue) {
        this.itemValue = itemValue;
    }

    public Integer getItemOrder() {
        return itemOrder;
    }

    public void setItemOrder(Integer itemOrder) {
        this.itemOrder = itemOrder;
    }

    public String getS3Url() {
        return s3Url;
    }

    public void setS3Url(String s3Url) {
        this.s3Url = s3Url;
    }

    public String getS3Key() {
        return s3Key;
    }

    public void setS3Key(String s3Key) {
        this.s3Key = s3Key;
    }

    public UUID getAdminSlideItemId() {
        return adminSlideItemId;
    }

    public void setAdminSlideItemId(UUID adminSlideItemId) {
        this.adminSlideItemId = adminSlideItemId;
    }
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

}
