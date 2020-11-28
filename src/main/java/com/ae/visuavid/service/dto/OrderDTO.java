package com.ae.visuavid.service.dto;

import java.util.List;
import java.util.UUID;

public class OrderDTO implements BaseDTO {
    private UUID id;
    private UUID userId;
    private String status;
    private List<LineDTO> lines;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<LineDTO> getLines() {
        return lines;
    }

    public void setLines(List<LineDTO> lines) {
        this.lines = lines;
    }
}
