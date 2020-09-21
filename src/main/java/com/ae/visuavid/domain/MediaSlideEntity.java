package com.ae.visuavid.domain;

import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import lombok.Data;

@Entity
@Table(name = "media")
@Data
public class MediaSlideEntity  extends AbstractAuditingEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	private UUID id;
	
	@Column(name = "screen_shot_s3_url")
	private String screenShotS3Url;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "media_id")
	private MediaEntity media;
	
	@OneToMany(mappedBy = "mediaSlide", fetch = FetchType.LAZY)
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private List<SlideItemEntity> mediaSlides;
	
}
