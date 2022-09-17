package com.idangdev.catalog.service;

import com.idangdev.catalog.domain.Publisher;
import com.idangdev.catalog.dto.PublisherCreateRequestDTO;
import com.idangdev.catalog.dto.PublisherListResponseDTO;
import com.idangdev.catalog.dto.PublisherResponseDTO;
import com.idangdev.catalog.dto.PublisherUpdateRequestDTO;
import com.idangdev.catalog.dto.ResultPageResponseDTO;

public interface PublisherService {
	
	public void createPublisher(PublisherCreateRequestDTO dto);
	
	public Publisher findPublisher(String publisherId);
	
	public void updatePublisher(String publisherId, PublisherUpdateRequestDTO dto);
	
	// pages = anda ingin melihat di halaman berapa
	// limit = dimana satu halaman ini ingin berapa elemen yang akan ditampilkan
	// sortBy = ingin di urutkan berdasarkan kolom yang mana
	// direction = ASCENDING ato DISCENDING
	// publisherName = filter berdasarkan nama publisher nya
	public ResultPageResponseDTO<PublisherListResponseDTO> findPublisherList(Integer pages, Integer limit, String sortBy, String direction, String publisherName);
	
	public PublisherResponseDTO constructDTO(Publisher publisher);
}
