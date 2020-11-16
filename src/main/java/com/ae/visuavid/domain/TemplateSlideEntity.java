package com.ae.visuavid.domain;

import java.util.List;
import java.util.UUID;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "template_slide")
public class TemplateSlideEntity extends AbstractAuditingEntity implements BaseEntity {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id")
    private UUID id;

    @Column(name = "screen_shot_s3_url")
    private String screenShotS3Url;

    @Column(name = "screen_shot_s3_key")
    private String screenShotS3Key;

    @Column(name = "slide_name")
    private String slideName;

    @Column(name = "slide_order")
    private Integer slideOrder;

    @Column(name = "instructions")
    private String instructions;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "template_id")
    private TemplateEntity template;

    @OneToMany(mappedBy = "templateSlide", fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private List<TemplateSlideItemEntity> slideItems;

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

    public String getScreenShotS3Key() {
        return screenShotS3Key;
    }

    public void setScreenShotS3Key(String screenShotS3Key) {
        this.screenShotS3Key = screenShotS3Key;
    }

    public String getSlideName() {
        return slideName;
    }

    public void setSlideName(String slideName) {
        this.slideName = slideName;
    }

    public Integer getSlideOrder() {
        return slideOrder;
    }

    public void setSlideOrder(Integer slideOrder) {
        this.slideOrder = slideOrder;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public TemplateEntity getTemplate() {
        return template;
    }

    public void setTemplate(TemplateEntity template) {
        this.template = template;
    }

    public List<TemplateSlideItemEntity> getSlideItems() {
        return slideItems;
    }

    public void setSlideItems(List<TemplateSlideItemEntity> slideItems) {
        this.slideItems = slideItems;
    }
}
