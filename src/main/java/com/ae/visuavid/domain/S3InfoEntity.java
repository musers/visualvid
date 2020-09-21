package com.ae.visuavid.domain;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "s3_info")
public class S3InfoEntity  extends AbstractAuditingEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	private UUID id;
	
	@Column(name = "media_name")
	private String name;
	
	@Column(name = "media_s3_key")
	private String s3Key;
	
	@Column(name = "url")
	private String url;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "media_size")
	private Integer size;
	
	@OneToOne(mappedBy = "s3Info")
	private MediaEntity media;

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

	public String getS3Key() {
		return s3Key;
	}

	public void setS3Key(String s3Key) {
		this.s3Key = s3Key;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

	
	
}
