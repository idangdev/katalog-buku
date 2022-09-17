package com.idangdev.catalog.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class BookQueryDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8262691631868574815L;

	private Long id;
	
	private String bookId;	// secureId
	
	private String bookTitle;
	
	private String publisherName;
	
	private String description;
	
}
