package com.idangdev.catalog.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "publisher")
public class Publisher extends AbstractBaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4116090461774592052L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "publisher_generator")
	@SequenceGenerator(name = "publisher_generator", sequenceName = "publisher_id_seq")
	private Long id;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "company_name", nullable = false)
	private String companyName;
	
	@Column(name = "address")
	private String address;
	
	@OneToMany(mappedBy = "publisher")
	private List<Book> books;	// opsional (jika ingin relasi 2 arah)

}
