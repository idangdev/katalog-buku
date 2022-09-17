package com.idangdev.catalog.dto;

import java.io.Serializable;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AddressUpdateRequestDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8031499911975082431L;
	
	private Long addressId;		// tetap tambahkan addressId untuk menentukan Address yang mana yang mau di Update

	private String streetName;
	
	private String cityName;
	
	private String zipCode;
	
}
