package com.idangdev.catalog.service;

import java.util.List;

import com.idangdev.catalog.dto.BookCreateRequestDTO;
import com.idangdev.catalog.dto.BookDetailResponseDTO;
import com.idangdev.catalog.dto.BookListResponseDTO;
import com.idangdev.catalog.dto.BookUpdateRequestDTO;
import com.idangdev.catalog.dto.ResultPageResponseDTO;

public interface BookService {

	public BookDetailResponseDTO findBookDetailById(String id);
	
	public List<BookDetailResponseDTO> findBookListDetail();
	
	public void createNewBook(BookCreateRequestDTO dto);
	
	public void updateBook(Long bookId, BookUpdateRequestDTO dto);
	
	public void deleteBook(Long bookId);
	
	public ResultPageResponseDTO<BookListResponseDTO> findBookList(Integer page, Integer limit, String sortBy,
			String direction, String publisherName, String bookTitle, String authorName);
}
