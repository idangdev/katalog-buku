package com.idangdev.catalog.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.idangdev.catalog.domain.Author;
import com.idangdev.catalog.dto.AuthorQueryDTO;

public interface AuthorRepository extends JpaRepository<Author, Long>{
	
	//method name convention
	//find+keyword
	// sql -> select * from Author a where a.id= :authorId
	public Optional<Author> findById(Long id);	// optional itu best practice untuk mencegah terjadinya null pointer exception
	
	public List<Author> findBySecureIdIn(List<String> authorIdList); 	// disini pake IN untuk mendapatkan data lebih dari 1
	
	public Optional<Author> findBySecureId(String id);
	
	// sql -> where id = :id AND deleted=false
	public Optional<Author> findByIdAndDeletedFalse(Long id);
	
	// sql -> select * from Author a where a.name LIKE :authorName
	public List<Author> findByNameLike(String name);
	
	@Query("SELECT new com.idangdev.catalog.dto.AuthorQueryDTO(b.id, ba.name) "
			+ "FROM Book b "
			+ "JOIN b.authors ba "
			+ "WHERE b.id IN :bookIdList")
	public List<AuthorQueryDTO> findAuthorsByBookIdList(List<Long> bookIdList);
	
}
