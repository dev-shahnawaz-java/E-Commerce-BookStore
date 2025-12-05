package com.nawaz.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="ratings")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ratings {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="rate")
	private int rate;
	
	@Column(name="reviewText")
	private String reviewText;
	
	@ManyToOne
	@JoinColumn(name="book_id", updatable = false)
	private BooksModule booksModule;
	
	@ManyToOne
	@JoinColumn(name="customer_id", updatable = false)
	private Customer customer;
	
	@CreationTimestamp
	@Column(name="createdDate", insertable=false, updatable=false)
	public LocalDateTime createdDate;
	@Column(name="createdDate")
	public LocalDateTime updatedDate;

	
	
	
	

}
