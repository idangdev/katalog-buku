package com.idangdev.catalog.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "book")
public class Book extends AbstractBaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4084431024874301974L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "title", nullable = false)
	private String title;
	
	@Column(name = "description", nullable = true, columnDefinition = "TEXT")
	private String description;	// default VARCHAR(255)
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "publisher_id", nullable = false)
	private Publisher publisher;
	
	@ManyToMany
	@JoinTable(name = "book_author", joinColumns = {
			@JoinColumn(name = "book_id", referencedColumnName = "id")},
	inverseJoinColumns = {
			@JoinColumn(name = "author_id", referencedColumnName = "id")
	})
	private List<Author> authors;
	
	@ManyToMany
	@JoinTable(name = "book_category", joinColumns = {
			@JoinColumn(name = "book_id", referencedColumnName = "id")},
	inverseJoinColumns = {
			@JoinColumn(name = "category_code", referencedColumnName = "code")
	})
	private List<Category> categories;
	
}
