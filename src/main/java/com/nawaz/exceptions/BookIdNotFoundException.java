package com.nawaz.exceptions;

public class BookIdNotFoundException extends Exception {
	
private static final long serialVersionUID = 1L;
	
	public BookIdNotFoundException(String msg) {
		super(msg);
	}

}
