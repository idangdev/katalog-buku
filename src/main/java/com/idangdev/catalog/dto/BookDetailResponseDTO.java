package com.idangdev.catalog.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class BookDetailResponseDTO implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 8743116631616606596L;

	private String bookId;

    private List<AuthorResponseDTO> authors;
    
    private List<CategoryListResponseDTO> categories;
    
    private PublisherResponseDTO publisher;

    private String bookTitle;

    private String bookDescription;
	
}