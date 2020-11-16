package com.ae.visuavid.service.dto;

import java.util.List;
import java.util.UUID;

public class TemplateDTO implements BaseDTO {
    private UUID id;
    private UUID adminMediaId;
    private List<TemplateSlideDTO> slides;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getAdminMediaId() {
        return adminMediaId;
    }

    public void setAdminMediaId(UUID adminMediaId) {
        this.adminMediaId = adminMediaId;
    }

    public List<TemplateSlideDTO> getSlides() {
        return slides;
    }

    public void setSlides(List<TemplateSlideDTO> slides) {
        this.slides = slides;
    }
}
