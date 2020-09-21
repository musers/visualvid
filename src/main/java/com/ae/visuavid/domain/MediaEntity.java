package com.ae.visuavid.domain;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import lombok.Data;

@Entity
@Table(name = "media")
@Data
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
	
	
}
