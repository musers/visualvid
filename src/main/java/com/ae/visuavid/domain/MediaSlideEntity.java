package com.ae.visuavid.domain;

import java.util.List;
import java.util.UUID;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "media_slide")
public class MediaSlideEntity extends AbstractAuditingEntity implements BaseEntity {
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "media_id")
    private AdminMediaEntity media;

    @OneToMany(mappedBy = "mediaSlide", fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE })
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private List<SlideItemEntity> slideItems;

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

    public AdminMediaEntity getMedia() {
        return media;
    }

    public void setMedia(AdminMediaEntity media) {
        this.media = media;
    }

    public List<SlideItemEntity> getSlideItems() {
        return slideItems;
    }

    public void setSlideItems(List<SlideItemEntity> slideItems) {
        this.slideItems = slideItems;
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
}
