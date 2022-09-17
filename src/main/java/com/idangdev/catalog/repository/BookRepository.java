package com.idangdev.catalog.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.idangdev.catalog.domain.Book;
import com.idangdev.catalog.dto.BookQueryDTO;

public interface BookRepository extends JpaRepository<Book, Long> {

	//SQL : select b from book b WHERE b.secure_id = :id
	//JPQL : select b from Book b WHERE b.secureId = :id
	public Optional<Book> findById(Long id);
	
	public Optional<Book> findBySecureId(String id);

	// SQL : SELECT b FROM book b INNER JOIN publisher p ON p.id = b.pubsliher_id WHERE p.name = :publisherName AND b.title=:bookTitle
	/* JPQL SEBELUM PAKE JPA Projection
	 * @Query("SELECT DISTINCT b FROM Book b " +
	 * "INNER JOIN Publisher p ON p.id = b.publisher.id " + "JOIN b.authors ba " +
	 * "WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :publisherName, '%')) " +
	 * "AND LOWER(b.title) LIKE LOWER(CONCAT('%', :bookTitle, '%')) " +
	 * "AND LOWER(ba.name) LIKE LOWER(CONCAT('%', :authorName, '%'))")
	 */
	@Query("SELECT DISTINCT new com.idangdev.catalog.dto.BookQueryDTO(b.id, b.secureId, b.title, p.name, b.description) "
			+ "FROM Book b "
			+ "INNER JOIN Publisher p ON p.id = b.publisher.id "
			+ "JOIN b.authors ba "
			+ "WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :publisherName, '%')) "
			+ "AND LOWER(b.title) LIKE LOWER(CONCAT('%', :bookTitle, '%')) "
			+ "AND LOWER(ba.name) LIKE LOWER(CONCAT('%', :authorName, '%'))")
	public Page<BookQueryDTO> findBookList(String bookTitle, String publisherName, String authorName, Pageable pageable);	// nama method nya bebas karena pake JPQL
	
//	public List<Book> findAll();
//	
//	public void save(Book book);
//	
//	public void update(Book book);
//	
//	public void delete(Long bookId);
	
}
