package com.nareshit.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class BooksDto {
	
	
	private Long id;
	private String name;
	private String title;
	private String author;
	
	public LocalDateTime createdDate;
	
	public LocalDateTime updatedDate;

	
	

	
	

}
