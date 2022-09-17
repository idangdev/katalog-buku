package com.idangdev.catalog.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.idangdev.catalog.validator.annotation.ValidAuthorName;

import lombok.Data;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Data
public class AuthorCreateRequestDTO {

	@ValidAuthorName
	@NotBlank
	private String authorName;
	
	@NotNull
	private Long birthDate;
	
	@NotEmpty
	private List<AddressCreateRequestDTO> addresses;
	
}
