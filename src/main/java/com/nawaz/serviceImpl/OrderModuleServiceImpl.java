package com.nawaz.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nawaz.entity.BooksModule;
import com.nawaz.entity.Order;
import com.nawaz.entity.UserRegister;
import com.nawaz.model.OrderModuleDto;
import com.nawaz.repository.OrderModuleRepo;
import com.nawaz.repository.UserRegisterRepo;
import com.nawaz.service.OrderModuleService;


@Service
public class OrderModuleServiceImpl implements OrderModuleService {

	@Autowired
	private UserRegisterRepo userRepo;
	
	@Autowired
	private OrderModuleRepo orderRepo;
	
	
	@Override
	public String saveOrders(OrderModuleDto orderModuleDto) {

		// 1. check if the request body or titles are empty
				if (orderModuleDto == null || orderModuleDto.getTitle() == null || orderModuleDto.getTitle().isEmpty()) {
					return "No books selected. Please select at least one book to proceed.";
				}
				
		// 2. Extract customer ID and selected book titles
				Long custmerId = orderModuleDto.getCustmerId();
				List<String> seletedBooks = orderModuleDto.getTitle();
				
		//3. check whether the user is prime member or not
				
				Boolean ifPrimeUser = checkPrimeUser(custmerId);
		
	    //Apply rules for Non-prime users
				
				if(!ifPrimeUser) {
					//non-prime users cannot order more than one book at atime
					
					if(seletedBooks.size() > 1) {
						return "Non-Prime users can select only one book.";
					}
					
					// Non-prime users can place only one order per week
					List<Order> anyLastweekPlaced = orderRepo.findAnyLastweekPlaced(custmerId);

					if (!anyLastweekPlaced.isEmpty()) {
						return "Non-prime users can place only one order per week.";
					}
				}
				
				
				// 5. Iterate through each selected book and validate availability
				for (String title : seletedBooks) {

					// Find book details by title
					BooksModule bookName = orderRepo.findByBookName(title);

					// If the book doesn't exist, return a message
					if (bookName == null) {
						return "No book found: " + title;
					}

					// Create a new order entry for the customer
					Order order = new Order();
					order.setBookId(bookName.getId());
					order.setCustomerId(custmerId);
					order.setStatus(false); // Default status = pending/unprocessed

					// Save order into database
					orderRepo.save(order);
				}

				// 6. Return final success message
				return "Order Placed successfully. Thank you.!";
	}
	
	
	//Helper method to check whther the user is prime or not
	private Boolean checkPrimeUser(Long custmerId) {

		// Fetch user by ID
		Optional<UserRegister> user = userRepo.findById(custmerId);

		// Return Prime status if available, else default to false
		return user.map(UserRegister::getPrime).orElse(false);
	}
	


}
