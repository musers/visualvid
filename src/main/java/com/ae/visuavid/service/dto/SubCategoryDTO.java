package com.ae.visuavid.service.dto;

import java.util.UUID;
import javax.validation.constraints.NotNull;

public class SubCategoryDTO implements BaseDTO {
    private UUID id;

    @NotNull
    private String categoryId;

    @NotNull
    private String subCategoryName;

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

    public String getSubCategoryName() {
        return subCategoryName;
    }

    public void setSubCategoryName(String subCategoryName) {
        this.subCategoryName = subCategoryName;
    }
}
