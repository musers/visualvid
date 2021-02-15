package com.ae.visuavid.domain;

import java.util.List;
import java.util.UUID;
import javax.persistence.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "category")
public class CategoryEntity extends AbstractAuditingEntity implements BaseEntity {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id")
    private UUID id;

    @Column(name = "category_name")
    private String name;

    @Column(name = "s3_cover_image_key")
    private String s3CoverImageKey;

    @Column(name = "s3_cover_image_url")
    private String s3CoverImageUrl;

    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private List<SubCategoryEntity> subCategories;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public List<SubCategoryEntity> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(List<SubCategoryEntity> subCategories) {
        this.subCategories = subCategories;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
