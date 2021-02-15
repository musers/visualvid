package com.ae.visuavid.service.dto;

import java.util.List;
import java.util.UUID;
import javax.persistence.Column;

public class CategoryDTO implements BaseDTO {
    List<SubCategoryDTO> subCategories;
    private UUID id;
    private String name;
    private String s3CoverImageKey;
    private String s3CoverImageUrl;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public List<SubCategoryDTO> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(List<SubCategoryDTO> subCategories) {
        this.subCategories = subCategories;
    }

    public String getS3CoverImageKey() {
        return s3CoverImageKey;
    }

    public void setS3CoverImageKey(String s3CoverImageKey) {
        this.s3CoverImageKey = s3CoverImageKey;
    }

    public String getS3CoverImageUrl() {
        return s3CoverImageUrl;
    }

    public void setS3CoverImageUrl(String s3CoverImageUrl) {
        this.s3CoverImageUrl = s3CoverImageUrl;
    }
}
