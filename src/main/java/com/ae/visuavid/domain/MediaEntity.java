package com.ae.visuavid.domain;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "media")
public class MediaEntity  extends AbstractAuditingEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
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
	
	@OneToMany(mappedBy = "media",fetch = FetchType.LAZY)
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private List<MediaSlideEntity> medias;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "s3_info_id")
	private S3InfoEntity s3Info;
	
}
