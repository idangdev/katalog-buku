package com.idangdev.catalog.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Data;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Data
public class ResultPageResponseDTO<T> implements Serializable{	// PILOT UNTUK PAGGING

	/**
	 * 
	 */
	private static final long serialVersionUID = 6310560327508386661L;

	private List<T> result;
	
	private Integer pages;	// jumlah halaman yang akan di tampilkan pada result tersebut
	
	private Long elements; // yang meng indikasikan jumlah dari records yang dapat ditampilkan dalam suatu query
}
