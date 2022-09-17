package com.idangdev.catalog.dto;


import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;

import com.idangdev.catalog.enums.ErrorCode;

import lombok.Data;

@Data
public class ErrorResponseDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4054533132278854024L;

	private Date timeStamp;
	
	private String message;
	
	private ErrorCode errorCode;
	
	private List<String> details;
	
	private HttpStatus status;
	
	// Method tambahan untuk meng construct errorResponseDTO
	public static ErrorResponseDTO of(final String message, List<String> details, final ErrorCode errorCode, HttpStatus status) {
		return new ErrorResponseDTO(message, errorCode, details, status);
	}

	public ErrorResponseDTO(String message, ErrorCode errorCode, List<String> details, HttpStatus status) {
		super();
		this.message = message;
		this.errorCode = errorCode;
		this.details = details;
		this.status = status;
		this.timeStamp = new Date();
	}
	
	
}
