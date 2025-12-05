package com.nawaz.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RatingsDto {
	
	private Integer customerId;
	private Long BookId;
	private int rate;
	private String reviewText;
	
}
