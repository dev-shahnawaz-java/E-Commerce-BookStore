package com.nawaz.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@Table(name="orders")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long bookId;
	private Long customerId;
	private Boolean status;
	
	@CreationTimestamp
	@Column(name="createdDate" , updatable = false)
	public LocalDateTime createdDate;
	
	@Column(name="upateDate" , updatable = false)
	@UpdateTimestamp
	public LocalDateTime upateDate;

}