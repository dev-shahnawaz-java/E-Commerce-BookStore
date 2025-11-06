package com.nawaz.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nawaz.entity.BooksModule;
import com.nawaz.entity.CartModule;
import com.nawaz.entity.Customer;
import com.nawaz.exceptions.BookIdNotFoundException;
import com.nawaz.exceptions.CustomerNotFoundException;
import com.nawaz.repository.BooksServiceRepository;
import com.nawaz.repository.CartModuleRepositotry;
import com.nawaz.repository.CustomerRepo;
import com.nawaz.service.CartModuleService;


@Service
public class CartModuleServiceImpl implements CartModuleService {

	@Autowired
	private CartModuleRepositotry cartModuleRepo;
	
	@Autowired
	private CustomerRepo customerRepo;
	
	@Autowired
	private BooksServiceRepository bookRepo;
	
	@Override
	public CartModule addToCartBooks(Integer customerId, Long bookId, int quantity) {
		
	//  Step 1: Check if customer exists in DB using ID
    // If not found, throw custom exception "Customer Id Not Found"
	
			Customer customer = customerRepo.findById(customerId)
					            .orElseThrow(() -> new CustomerNotFoundException("Customer id not found"));
	//step 2:check if the book exists in db using id
  // if not found, throw custom exception "Book id not found";
			
			BooksModule bookModule = null;
			try {
				bookModule = bookRepo.findById(bookId)
						                .orElseThrow(() -> new BookIdNotFoundException("Book id is not found"));
			} catch (BookIdNotFoundException e) {
				
				e.getMessage();
			}

		//  Step 3: Check whether this customer already added this book in their cart
		    // It helps to avoid duplicate entries for the same book
			
			
			CartModule cartItem = cartModuleRepo.findByCustomerAndBooksModule(customer, bookModule);	
			
		//  Step 4: If cartItem already exists, update quantity
		    if (cartItem != null) {
		        // Add the new quantity to existing one
		        cartItem.setQuantity(cartItem.getQuantity() + quantity);
		    } 	
		    
		    
		//  Step 5: If cartItem not found, create new cart record
		    else {
		        // Create new CartModule object with quantity, book, and customer
		        cartItem = new CartModule(quantity, bookModule, customer);
		    }
		  
		    
		//  Step 6: Calculate total price for that book (quantity × book price)
		    cartItem.setTotalPrice(cartItem.getQuantity() * bookModule.getPrice());
		    
		    
		    //  Step 7: Save updated/created cart record to DB
		    // This ensures cart data is persistent in database
		    return cartModuleRepo.save(cartItem);
			
			
	}

}
























