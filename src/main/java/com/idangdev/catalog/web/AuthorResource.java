package com.idangdev.catalog.web;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.idangdev.catalog.dto.AuthorCreateRequestDTO;
import com.idangdev.catalog.dto.AuthorResponseDTO;
import com.idangdev.catalog.dto.AuthorUpdateRequestDTO;
import com.idangdev.catalog.service.AuthorService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;

@Validated
@AllArgsConstructor
@RestController
@SecurityRequirement(name = "bearerAuth")
public class AuthorResource {

	private final AuthorService authorService;
	
	//author detail
	@GetMapping("/v1/author/{id}/detail")
	public ResponseEntity<AuthorResponseDTO> findAuthorById(@PathVariable String id) {
		AuthorResponseDTO result = authorService.findAuthorById(id);
		return ResponseEntity.ok().body(result);
	}
	
	@PostMapping("/v1/author")
	public ResponseEntity<Void> createNewAuthor(@RequestBody @Valid List<AuthorCreateRequestDTO> dto) {
		authorService.createNewAuthor(dto);
		return ResponseEntity.created(URI.create("/author")).build();
	}
	
	@PutMapping("/v1/author/{authorId}")
	public ResponseEntity<Void> updateAuthor(@PathVariable String authorId, @RequestBody AuthorUpdateRequestDTO dto) {
		authorService.updateAuthor(authorId, dto);
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/v1/author/{authorId}")
	public ResponseEntity<Void> deleteAuthor(@PathVariable String authorId) {
		authorService.deleteAuthor(authorId);
		return ResponseEntity.ok().build();
	}
	
}
