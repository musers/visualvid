package com.ae.visuavid.service.dto;

import java.util.List;
import java.util.UUID;
import javax.persistence.Column;

public class TemplateSlideDTO implements BaseDTO {
    private UUID id;
    private String screenShotS3Url;
    private String screenShotS3Key;
    private String slideName;
    private Integer slideOrder;
    private String instructions;
    private List<TemplateSlideItemDTO> slideItems;

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

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public List<TemplateSlideItemDTO> getSlideItems() {
        return slideItems;
    }

    public void setSlideItems(List<TemplateSlideItemDTO> slideItems) {
        this.slideItems = slideItems;
    }
}
