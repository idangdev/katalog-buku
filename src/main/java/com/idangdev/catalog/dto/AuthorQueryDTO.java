package com.idangdev.catalog.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class AuthorQueryDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5904135532617472506L;

	private Long bookId;
	
	private String authorName;
	
}
