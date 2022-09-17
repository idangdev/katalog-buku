package com.idangdev.catalog.repository.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.idangdev.catalog.domain.Book;
import com.idangdev.catalog.repository.BookRepository;

import lombok.Data;

@Data
public class BookRepositoryImpl {
//	
//	private Map<Long, Book> bookMap;
//	
//	@Override
//	public Book findBookById(Long id) {
//		Book book = bookMap.get(id);
//		return book;
//	}
//
//	@Override
//	public List<Book> findAll() {
//		List<Book> bookList = new ArrayList<>(bookMap.values());
//		return bookList;
//	}
//
//	@Override
//	public void save(Book book) {
//		// size = 2, -> 1,2
//		int size = bookMap.size();
//		book.setId((long) (size + 1));
//		bookMap.put(book.getId(), book);
//	}
//
//	@Override
//	public void update(Book book) {
//		bookMap.put(book.getId(), book);
//		
//	}
//
//	@Override
//	public void delete(Long bookId) {
//		bookMap.remove(bookId);		
//	}
//	
//	

}
