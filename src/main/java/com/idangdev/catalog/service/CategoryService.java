package com.idangdev.catalog.service;

import java.util.List;
import java.util.Map;

import com.idangdev.catalog.domain.Category;
import com.idangdev.catalog.dto.CategoryCreateUpdateRequestDTO;
import com.idangdev.catalog.dto.CategoryListResponseDTO;
import com.idangdev.catalog.dto.ResultPageResponseDTO;

public interface CategoryService {

	public void createAndUpdateCategory(CategoryCreateUpdateRequestDTO dto);
	
	public ResultPageResponseDTO<CategoryListResponseDTO> findCategoryList(Integer pages,
			Integer limit, String sortBy, String direction, String categoryName);
	
	public List<Category> findCategories(List<String> categoryCodeList);
	
	public List<CategoryListResponseDTO> constructDTO(List<Category> categories);
	
	public Map<Long, List<String>> findCategoriesMap(List<Long> bookIdList);
}
