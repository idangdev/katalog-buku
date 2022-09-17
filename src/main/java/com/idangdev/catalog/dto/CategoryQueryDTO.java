package com.idangdev.catalog.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CategoryQueryDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1438241827899521868L;

	private Long bookId;	// berada di Entity Book
	
	private String categoryCode;	// berada di Entity Category	disini kita memerlukan JPA projection
	
}
