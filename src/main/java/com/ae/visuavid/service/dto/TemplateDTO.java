package com.ae.visuavid.service.dto;

import java.util.List;
import java.util.UUID;

public class TemplateDTO implements BaseDTO {
    private UUID id;
    private UUID adminMediaId;
    private List<TemplateSlideDTO> userSlides;

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

    public List<TemplateSlideDTO> getUserSlides() {
        return userSlides;
    }

    public void setUserSlides(List<TemplateSlideDTO> userSlides) {
        this.userSlides = userSlides;
    }
}
