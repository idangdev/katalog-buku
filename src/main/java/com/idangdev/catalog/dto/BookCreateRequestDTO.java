package com.idangdev.catalog.dto;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)	// kalo nama nya pengen snake_case
public class BookCreateRequestDTO implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 316071968569670071L;

	@NotBlank
	private String bookTitle;
	
	@NotEmpty
	private List<String> authorIdList;	// kita pake List<String> karena yang 1 buku tidak hanya ditulis oleh 1 orang melainkan bisa banyak
	
	@NotEmpty
	private List<String> categoryList;
	
	@NotBlank
	private String publisherId;
	
	@JsonProperty("synopsis")
	private String description;
	
}
