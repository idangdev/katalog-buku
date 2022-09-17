package com.idangdev.catalog.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class LoginRequestDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6135843286586527298L;

	private String username;
	
	private String password;
	
}
