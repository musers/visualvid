package com.ae.visuavid.service.dto;

import javax.validation.constraints.NotNull;
import java.util.UUID;

public class SubCategoryDTO implements BaseDTO {
    private UUID id;

    @NotNull
    private String categoryId;

    private String categoryName;

    @NotNull
    private String name;

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
