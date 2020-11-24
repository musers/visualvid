package com.ae.visuavid.service.dto;

import java.util.UUID;

public class LineDTO implements BaseDTO {
    private UUID id;
    private UUID templateId;
    private UUID adminMediaId;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getTemplateId() {
        return templateId;
    }

    public void setTemplateId(UUID templateId) {
        this.templateId = templateId;
    }

    public UUID getAdminMediaId() {
        return adminMediaId;
    }

    public void setAdminMediaId(UUID adminMediaId) {
        this.adminMediaId = adminMediaId;
    }
}
