package com.idangdev.catalog.domain;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Index;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;

import lombok.Data;

@Data
@MappedSuperclass
@Table(indexes = { 
	@Index(name="uk_secure_id", columnList = "secure_id")	// kita set secure_id pada line 45 menjadi index dengan nama uk_secure_id
})	
public abstract class AbstractBaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3060939495907508889L;

	@Column(name = "secure_id", nullable = false, unique = true)
	private String secureId = UUID.randomUUID().toString();
	
	@Column(name = "deleted", columnDefinition = "boolean default false")
	private boolean deleted;
	
}
