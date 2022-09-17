package com.idangdev.catalog.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.idangdev.catalog.domain.Publisher;

public interface PublisherRepository extends JpaRepository<Publisher, Long> {

	public Optional<Publisher> findBySecureId(String secureId);
	
	// untuk meng QUERY data berdasarkan PAGGING
	// ignoreCase = mengabaikan besar kecil dari pencariannya
	// pageable = argumen untuk mendapatkan paging nya
	public Page<Publisher> findByNameLikeIgnoreCase(String publisherName, Pageable pageable);
	
}
