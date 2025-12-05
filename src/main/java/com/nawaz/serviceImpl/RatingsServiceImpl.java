package com.nawaz.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nawaz.entity.BooksModule;
import com.nawaz.entity.Customer;
import com.nawaz.entity.Ratings;
import com.nawaz.exceptions.BookIdNotFoundException;
import com.nawaz.exceptions.CustomerNotFoundException;
import com.nawaz.model.RatingsDto;
import com.nawaz.repository.BooksServiceRepository;
import com.nawaz.repository.CustomerRepo;
import com.nawaz.repository.RatingsRepo;
import com.nawaz.service.RatingsService;

@Service
public class RatingsServiceImpl implements RatingsService {
	
	@Autowired
	private CustomerRepo customerRepo;
	
	@Autowired
	private BooksServiceRepository bookServiceRepo;
	
	@Autowired
	private RatingsRepo retingsRepo;

	@Override
	public Ratings createRatingReviews(RatingsDto ratingDto) {
		
		//check if customer exist in db using id
		
		Customer customer = customerRepo.findById(ratingDto.getCustomerId())
				 .orElseThrow(() -> new CustomerNotFoundException("Customer id not found"));
		
		//check book exist in db or not
		
		BooksModule bookModule = null;
		try {
			bookModule = bookServiceRepo.findById(ratingDto.getBookId())
					.orElseThrow(() -> new BookIdNotFoundException("Book is not found"));
		} catch (BookIdNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Ratings rr = new Ratings();
		rr.setBooksModule(bookModule);
		rr.setCustomer(customer);
		rr.setRate(ratingDto.getRate());
		rr.setReviewText(ratingDto.getReviewText());
		
		retingsRepo.save(rr);
		
		
		return rr;
	}

	@Override
	public List<Ratings> getByAllReviews() {
		
		return retingsRepo.findAll();
	}

}
