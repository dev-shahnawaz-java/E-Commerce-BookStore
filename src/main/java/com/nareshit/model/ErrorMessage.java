package com.nareshit.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ErrorMessage {
	

	private Integer statusCode;
	private String status;
	private String message;
	private List<?> list;
	
	public ErrorMessage(Integer statusCode, String status, String message, List<?> list) {
		super();
		this.statusCode = statusCode;
		this.status = status;
		this.message = message;
		this.list = list;
	}
	
	
	
	

}
