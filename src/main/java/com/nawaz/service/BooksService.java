package com.nawaz.service;

import java.util.List;

import com.nawaz.model.BooksDto;


public interface BooksService{
	
	public BooksDto customerSaveBooks(BooksDto booksDto);
	public List<BooksDto> getAllBooks();
	
	public BooksDto getBookById(Long id);
	public BooksDto updateBook(Long id, BooksDto booksDto);
	public BooksDto createOrUpdateBook(BooksDto booksDto);
	public void deleteBookById(Long id);

}
