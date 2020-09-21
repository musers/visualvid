package com.ae.visuavid.domain;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "slide_items")
@Data
public class SlideItemEntity  extends AbstractAuditingEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	private UUID id;
	
	@Column(name = "type")
	private String type;
	
	@Column(name = "label")
	private String label;
	
	@Column(name = "media_order")
	private Integer order;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "media_slide_id")
	private MediaSlideEntity mediaSlide;
	
}
