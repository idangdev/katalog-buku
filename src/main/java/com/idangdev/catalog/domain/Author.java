package com.idangdev.catalog.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "author")	 // defaultnya nama table sama class entity nya itu sama. tp bisa diubah
//@DynamicUpdate
//@SQLDelete(sql = "UPDATE author SET deleted = true WHERE id = ?")
//@Where(clause = "deleted=false")
public class Author extends AbstractBaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5765324870191657026L;

	@Id	// secara otomatis kolom id jadi primary key
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "author_generator")
	@SequenceGenerator(name = "author_generator", sequenceName = "author_id_seq")
	private Long id;
	
	@Column(name = "author_name", nullable = false, columnDefinition = "varchar(300)") 	// memappingkan masing masing properti 
	private String name;	// yang ada pada class entiti disini ke dalam kolom2 yang ada di tabel author
	
	@Column(name = "birth_date", nullable = false)
	private LocalDate birthDate;
	
	@OneToMany(mappedBy = "author", cascade = CascadeType.ALL)		// mappedBy = nama properties yang ada di entity Address yaitu author
	private List<Address> addresses;
	
}
