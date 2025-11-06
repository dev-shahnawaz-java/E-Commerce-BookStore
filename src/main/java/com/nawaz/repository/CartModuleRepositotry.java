package com.nawaz.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nawaz.entity.BooksModule;
import com.nawaz.entity.CartModule;
import com.nawaz.entity.Customer;

public interface CartModuleRepositotry extends JpaRepository<CartModule, Long> {

	public CartModule findByCustomerAndBooksModule(Customer customer, BooksModule booksModule);
}
