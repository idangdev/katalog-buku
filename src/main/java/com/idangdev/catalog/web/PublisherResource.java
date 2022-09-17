package com.idangdev.catalog.web;

import java.net.URI;

import javax.validation.Valid;
import javax.validation.constraints.Size;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.idangdev.catalog.annotation.LogThisMethod;
import com.idangdev.catalog.dto.PublisherCreateRequestDTO;
import com.idangdev.catalog.dto.PublisherListResponseDTO;
import com.idangdev.catalog.dto.PublisherUpdateRequestDTO;
import com.idangdev.catalog.dto.ResultPageResponseDTO;
import com.idangdev.catalog.exception.BadRequestException;
import com.idangdev.catalog.service.PublisherService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;

@Validated
@RestController
@AllArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class PublisherResource {

	private final PublisherService publisherService;
	
	@PreAuthorize("hasRole('ADMIN')")	// hanya admin yang bisa mengakses
	@PostMapping("/v1/publisher")
	public ResponseEntity<Void> createNewPublisher(@RequestBody @Valid PublisherCreateRequestDTO dto){
		publisherService.createPublisher(dto);
		return ResponseEntity.created(URI.create("v1/publisher")).build();
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/v1/publisher/{publisherId}")
	public ResponseEntity<Void> updatePublisher(@PathVariable 
			@Size(max = 36, min = 36, message = "publisher.id.not.uuid") String publisherId, // validasi jika kurang / lebih dari 36 maka itu bukan uuid
			@RequestBody @Valid PublisherUpdateRequestDTO dto) {
		publisherService.updatePublisher(publisherId, dto);
		return ResponseEntity.ok().build();
	}
	
	@PreAuthorize("isAuthenticated()")	// semua user boleh mengakses
	@LogThisMethod
	@GetMapping("/v1/publisher")
	public ResponseEntity<ResultPageResponseDTO<PublisherListResponseDTO>> findPublisherList(
			@RequestParam(name = "pages", required = true, defaultValue = "0") Integer pages,
			@RequestParam(name = "limit", required = true, defaultValue = "10") Integer limit,
			@RequestParam(name = "sortBy", required = true, defaultValue = "name") String sortBy,	// ini maksudnya adalah di urutkan berdasarkan apa?. defaultnya adalah nama, jd ini diurutkan berdasarkan nama nya. dan ini harus sama dengan yang ada di entity Publisher
			@RequestParam(name = "direction", required = true, defaultValue = "asc") String direction,
			@RequestParam(name = "publisherName", required = false) String publisherName
			){
		if (pages<0) throw new BadRequestException("invalid pages");
		return ResponseEntity.ok().body(publisherService.findPublisherList(pages, limit, sortBy, direction, publisherName));
	}
}
