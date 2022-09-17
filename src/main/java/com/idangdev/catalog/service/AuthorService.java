package com.idangdev.catalog.service;

import java.util.List;
import java.util.Map;

import com.idangdev.catalog.domain.Author;
import com.idangdev.catalog.dto.AuthorCreateRequestDTO;
import com.idangdev.catalog.dto.AuthorResponseDTO;
import com.idangdev.catalog.dto.AuthorUpdateRequestDTO;

public interface AuthorService {

	public AuthorResponseDTO findAuthorById(String id);
	
	public void createNewAuthor(List<AuthorCreateRequestDTO> dto);
	
	public void updateAuthor(String authorId, AuthorUpdateRequestDTO dto);
	
	public void deleteAuthor(String authorId);
	
	public List<Author> findAuthors(List<String> authorIdList);
	
	public List<AuthorResponseDTO> constructDTO(List<Author> authors);
	
	public Map<Long, List<String>> findAuthorMaps(List<Long> bookIdList);
}
