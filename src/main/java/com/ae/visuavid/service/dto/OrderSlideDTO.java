package com.ae.visuavid.service.dto;

import java.util.List;
import java.util.UUID;

public class OrderSlideDTO implements BaseDTO {
    private UUID id;
    private String screenShotS3Url;
    private String screenShotS3Key;
    private String slideName;
    private Integer slideOrder;
    private UUID adminSlideId;
    private List<OrderSlideItemDTO> orderSlideItems;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getScreenShotS3Url() {
        return screenShotS3Url;
    }

    public void setScreenShotS3Url(String screenShotS3Url) {
        this.screenShotS3Url = screenShotS3Url;
    }

    public String getScreenShotS3Key() {
        return screenShotS3Key;
    }

    public void setScreenShotS3Key(String screenShotS3Key) {
        this.screenShotS3Key = screenShotS3Key;
    }

    public String getSlideName() {
        return slideName;
    }

    public void setSlideName(String slideName) {
        this.slideName = slideName;
    }

    public Integer getSlideOrder() {
        return slideOrder;
    }

    public void setSlideOrder(Integer slideOrder) {
        this.slideOrder = slideOrder;
    }

    public UUID getAdminSlideId() {
        return adminSlideId;
    }

    public void setAdminSlideId(UUID adminSlideId) {
        this.adminSlideId = adminSlideId;
    }

    public List<OrderSlideItemDTO> getOrderSlideItems() {
        return orderSlideItems;
    }

    public void setOrderSlideItems(List<OrderSlideItemDTO> orderSlideItems) {
        this.orderSlideItems = orderSlideItems;
    }

}
