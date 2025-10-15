package com.nareshit.exceptions;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.nareshit.model.ErrorMessage;
import com.nareshit.utility.Constants;

@RestControllerAdvice
public class GlobalExceptionhandle {
	
	@ExceptionHandler(CustomerNotFoundException.class)
	public ResponseEntity<Object> handleException(CustomerNotFoundException ex){
		
		List<String> list = new ArrayList<>();
		list.add("Error Details: Customer not found at this id ");
		list.add("Error Message: "+ ex.getLocalizedMessage());
		list.add("Timestamp: "+ System.currentTimeMillis());
		
		ErrorMessage err = new ErrorMessage(HttpURLConnection.HTTP_BAD_REQUEST,Constants.FAILURE,"Customer not found ",list);
		
		return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(InternalServerError.class)
	public ResponseEntity<Object> handleInternalServerError(InternalServerError er){
		
		List<String> list = new ArrayList<>();
		list.add("Error Details: Internal server error");
		list.add("Error Message: "+ er.getMessage());
		list.add("Timestamp: "+ System.currentTimeMillis());
		
        ErrorMessage err = new ErrorMessage(HttpURLConnection.HTTP_INTERNAL_ERROR,
        		Constants.FAILURE,"Internal server error",list);
		
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(err);
		
	}

}
