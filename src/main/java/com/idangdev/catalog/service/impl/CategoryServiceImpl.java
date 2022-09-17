package com.idangdev.catalog.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.idangdev.catalog.domain.Category;
import com.idangdev.catalog.dto.CategoryCreateUpdateRequestDTO;
import com.idangdev.catalog.dto.CategoryListResponseDTO;
import com.idangdev.catalog.dto.CategoryQueryDTO;
import com.idangdev.catalog.dto.ResultPageResponseDTO;
import com.idangdev.catalog.exception.BadRequestException;
import com.idangdev.catalog.repository.CategoryRepository;
import com.idangdev.catalog.service.CategoryService;
import com.idangdev.catalog.util.PaginationUtil;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService{

	private final CategoryRepository categoryRepository;
	
	@Override
	public void createAndUpdateCategory(CategoryCreateUpdateRequestDTO dto) {

		Category category = categoryRepository.findByCode(dto.getCode().toLowerCase()).orElse(new Category());
		if (category.getCode() == null) {
			category.setCode(dto.getCode().toLowerCase()); // new
		}
		category.setName(dto.getName());
		category.setDescription(dto.getDescription());
		categoryRepository.save(category);
	}

	@Override
	public ResultPageResponseDTO<CategoryListResponseDTO> findCategoryList(Integer pages, Integer limit, String sortBy,
			String direction, String categoryName) {
		categoryName = StringUtils.isEmpty(categoryName)?"%":categoryName+"%";
		Sort sort = Sort.by(new Sort.Order(PaginationUtil.getSortBy(direction), sortBy));
		Pageable pageable = PageRequest.of(pages, limit, sort);
		Page<Category> pageResult = categoryRepository.findByNameLikeIgnoreCase(categoryName, pageable);
		List<CategoryListResponseDTO> dtos = pageResult.stream().map((c) -> {
			CategoryListResponseDTO dto = new CategoryListResponseDTO();
			dto.setCode(c.getCode());
			dto.setName(c.getName());
			dto.setDescription(c.getDescription());
			
			return dto;
		}).collect(Collectors.toList());
		 
		return PaginationUtil.createResultPageDTO(dtos, pageResult.getTotalElements(), pageResult.getTotalPages());
	}

	@Override
	public List<Category> findCategories(List<String> categoryCodeList) {
		List<Category> categories = categoryRepository.findByCodeIn(categoryCodeList);
		if(categories.isEmpty()) 
			throw new BadRequestException("category cant empty");
		return categories;
	}

	@Override
	public List<CategoryListResponseDTO> constructDTO(List<Category> categories) {
		
		return categories.stream().map((c) -> {
			CategoryListResponseDTO dto = new CategoryListResponseDTO();
			dto.setCode(c.getCode());
			dto.setName(c.getName());
			dto.setDescription(c.getDescription());
			return dto;
		}).collect(Collectors.toList());
	}

	@Override
	public Map<Long, List<String>> findCategoriesMap(List<Long> bookIdList) {
		List<CategoryQueryDTO> queryList = categoryRepository.findCategoryByBookIdList(bookIdList);	// id, categoryCode
		Map<Long, List<String>> categoryMaps = new HashMap<>();
		List<String> categoryCodeList = null;
		for(CategoryQueryDTO q: queryList) {
			if (!categoryMaps.containsKey(q.getBookId())) {	// cek apakah empty ato tidak. misal kita cek 5. jika id 5 tidak ada. dengan method containsKey maka akan mendapatkan nilai FALSE. karena ada ! berubah jadi TRUE. dan dia akan membuat array baru
				categoryCodeList = new ArrayList<>();	// jika tidak ada kita buat array list baru
			}else {
				categoryCodeList = categoryMaps.get(q.getBookId());	// jika ada kita ambil category list tadi dari map nya
			}
			categoryCodeList.add(q.getCategoryCode());
			categoryMaps.put(q.getBookId(), categoryCodeList);	// map tidak bisa duplikat key nya. kalo duplikat akan di replace
		}
		return categoryMaps;
	}

}
