package com.ae.visuavid.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.UUID;

public class MediaSlideDTO {
    @JsonProperty("id")
    private UUID id;

    @JsonProperty("screenShotS3Url")
    private String screenShotS3Url;

    @JsonProperty("slideItems")
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
}
