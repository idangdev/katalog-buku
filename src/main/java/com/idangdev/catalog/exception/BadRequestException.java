package com.idangdev.catalog.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)							// akan otomatis memberikan response header nya
public class BadRequestException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4268608534497220682L;

	public BadRequestException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

}
