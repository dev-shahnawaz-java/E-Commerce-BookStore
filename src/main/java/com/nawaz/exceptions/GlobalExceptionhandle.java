package com.nawaz.exceptions;


import java.net.HttpURLConnection;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.nawaz.model.ErrorMessage;
import com.nawaz.model.ResponseMessage;
import com.nawaz.utility.Constants;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
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
	
	 @ExceptionHandler(ResourceNotFoundException.class)
	    public ResponseEntity<ResponseMessage> handleResourceNotFoundException(ResourceNotFoundException ex) {
	        log.error("Resource not found: {}", ex.getMessage());
	        
	        return ResponseEntity.status(HttpStatus.NOT_FOUND)
	            .body(new ResponseMessage(
	                HttpURLConnection.HTTP_NOT_FOUND,
	                Constants.FAILED,
	                ex.getMessage()
	            ));
	    }
	 
	 @ExceptionHandler(BookIdNotFoundException.class)
	    public ResponseEntity<ResponseMessage> handleBookIdNotFoundException(ResourceNotFoundException ex) {
	        log.error("Resource not found: {}", ex.getMessage());
	        
	        return ResponseEntity.status(HttpStatus.NOT_FOUND)
	            .body(new ResponseMessage(
	                HttpURLConnection.HTTP_NOT_FOUND,
	                Constants.FAILED,
	                ex.getMessage()
	            ));
	    }
	 
	 

}
