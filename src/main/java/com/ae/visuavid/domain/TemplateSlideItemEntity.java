package com.ae.visuavid.domain;

import java.util.UUID;
import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "template_slide_items")
public class TemplateSlideItemEntity extends AbstractAuditingEntity implements BaseEntity {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id")
    private UUID id;

    @Column(name = "item_type")
    private String itemType;

    @Column(name = "item_label")
    private String itemLabel;

    @Column(name = "item_value")
    private String itemValue;

    @Column(name = "item_order")
    private Integer itemOrder;

    @Column(name = "screen_shot_s3_url")
    private String screenShotS3Url;

    @Column(name = "screen_shot_s3_key")
    private String screenShotS3Key;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "template_slide_id")
    private TemplateSlideEntity templateSlide;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getItemLabel() {
        return itemLabel;
    }

    public void setItemLabel(String itemLabel) {
        this.itemLabel = itemLabel;
    }

    public Integer getItemOrder() {
        return itemOrder;
    }

    public void setItemOrder(Integer itemOrder) {
        this.itemOrder = itemOrder;
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

    public TemplateSlideEntity getTemplateSlide() {
        return templateSlide;
    }

    public void setTemplateSlide(TemplateSlideEntity templateSlide) {
        this.templateSlide = templateSlide;
    }

    public String getItemValue() {
        return itemValue;
    }

    public void setItemValue(String itemValue) {
        this.itemValue = itemValue;
    }
}
