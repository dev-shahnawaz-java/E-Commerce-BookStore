package com.nawaz.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BooksExcelFile {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "productName")
	private String productName;
	@Column(name = "description")
	private String description;
	@Column(name = "price")
	private Double price;
	
	
	@CreationTimestamp
	@Column(name = "createdDate")
	private LocalDateTime createdDate;
	
	@UpdateTimestamp
	@Column(name = "updatedDate")
	private LocalDateTime updateDate;
	

}
