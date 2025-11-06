package com.nawaz.entity.mongo;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection="Book_Mongo")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookModuleMongo {
	@Id
	private String id;
	private String name;
	private String title;
	private String author;
	
	@Column(name = "created_Date", updatable = false)
	@CreationTimestamp
	public LocalDateTime createdDate;
	
	@UpdateTimestamp
	@Column(name = "update_Date")
	public LocalDateTime updatedDate;

	

}
