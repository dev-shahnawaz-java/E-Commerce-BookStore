package com.nawaz.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartModule {
	
	private Long id;
	private int quantity;
	private double totalPrice;
	
	@ManyToOne
	@JoinColumn(name ="book_id",nullable=false)
	private BooksModule booksModule;
	
	@ManyToOne
	@JoinColumn(name="customer_id", nullable=false)
	private Customer customer;
	
	public CartModule(int quantity, BooksModule booksModule, Customer customer) {
		super();
		this.quantity = quantity;
		this.booksModule = booksModule;
		this.customer = customer;
	}
	
	@CreationTimestamp
	@Column(name = "createDate")
	private LocalDateTime createDate;

	@UpdateTimestamp
	@Column(name = "updateDate")
	private LocalDateTime updateDate;

}
