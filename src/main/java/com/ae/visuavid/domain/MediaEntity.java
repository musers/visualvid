package com.ae.visuavid.domain;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "media")
public class MediaEntity extends AbstractAuditingEntity {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id")
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "category")
    private String category;

    @Column(name = "description")
    private String description;

    @Column(name = "indian_price")
    private BigDecimal indianPrice;

    @Column(name = "usd_price")
    private BigDecimal usdPrice;

    @Column(name = "preview_video_s3_url")
    private String previewVideoS3Url;

    @Column(name = "thumb_nail_s3_url")
    private String thumbNailS3Url;

    @Column(name = "media_placeholders")
    private String mediaPlaceholder;

    @Column(name = "text_placeholders")
    private String textPlaceholder;

    @Column(name = "turn_around_time")
    private String turnAroundTime;

    @OneToMany(mappedBy = "media", fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private List<MediaSlideEntity> slides;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "s3_info_id")
    private S3InfoEntity s3Info;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getIndianPrice() {
        return indianPrice;
    }

    public void setIndianPrice(BigDecimal indianPrice) {
        this.indianPrice = indianPrice;
    }

    public BigDecimal getUsdPrice() {
        return usdPrice;
    }

    public void setUsdPrice(BigDecimal usdPrice) {
        this.usdPrice = usdPrice;
    }

    public String getPreviewVideoS3Url() {
        return previewVideoS3Url;
    }

    public void setPreviewVideoS3Url(String previewVideoS3Url) {
        this.previewVideoS3Url = previewVideoS3Url;
    }

    public String getThumbNailS3Url() {
        return thumbNailS3Url;
    }

    public void setThumbNailS3Url(String thumbNailS3Url) {
        this.thumbNailS3Url = thumbNailS3Url;
    }

    public String getMediaPlaceholder() {
        return mediaPlaceholder;
    }

    public void setMediaPlaceholder(String mediaPlaceholder) {
        this.mediaPlaceholder = mediaPlaceholder;
    }

    public String getTextPlaceholder() {
        return textPlaceholder;
    }

    public void setTextPlaceholder(String textPlaceholder) {
        this.textPlaceholder = textPlaceholder;
    }

    public String getTurnAroundTime() {
        return turnAroundTime;
    }

    public void setTurnAroundTime(String turnAroundTime) {
        this.turnAroundTime = turnAroundTime;
    }

    public S3InfoEntity getS3Info() {
        return s3Info;
    }

    public void setS3Info(S3InfoEntity s3Info) {
        this.s3Info = s3Info;
    }

    public List<MediaSlideEntity> getSlides() {
        return slides;
    }

    public void setSlides(List<MediaSlideEntity> slides) {
        this.slides = slides;
    }
}
