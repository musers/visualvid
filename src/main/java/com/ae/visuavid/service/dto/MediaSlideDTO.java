package com.ae.visuavid.service.dto;

import java.util.List;
import java.util.UUID;

public class MediaSlideDTO implements BaseDTO {
    private UUID id;

    private String screenShotS3Url;

    private String screenShotS3Key;

    private String slideName;

    private Integer slideOrder;

    private List<SlideItemDTO> slideItems;

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

    public List<SlideItemDTO> getSlideItems() {
        return slideItems;
    }

    public void setSlideItems(List<SlideItemDTO> slideItems) {
        this.slideItems = slideItems;
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
}
