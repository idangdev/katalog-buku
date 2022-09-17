package com.idangdev.catalog.util;

import java.util.List;

import org.springframework.data.domain.Sort;

import com.idangdev.catalog.dto.ResultPageResponseDTO;

public class PaginationUtil {
	
	public static <T> ResultPageResponseDTO<T> createResultPageDTO(List<T> dtos, Long totalElements, Integer pages){
		ResultPageResponseDTO<T> result = new ResultPageResponseDTO<T>();
		result.setResult(dtos);
		result.setPages(pages);
		result.setElements(totalElements);
		return result;
	}

	public static Sort.Direction getSortBy(String sortBy){
		if (sortBy.equals("asc")) {
			return Sort.Direction.ASC;
		}else {
			return Sort.Direction.DESC;
		}
	}
}
