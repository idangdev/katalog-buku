package com.idangdev.catalog.dto;

import java.io.Serializable;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PublisherListResponseDTO implements Serializable{		// MERUPAKAN PILOT UNTUK RESOURCE NYA / PER ELEMENT NYA
	/**
	 * 
	 */
	private static final long serialVersionUID = 8452682929784041096L;

	
	private String publisherId;
	
	private String publisherName;
	
	private String companyName;

}
