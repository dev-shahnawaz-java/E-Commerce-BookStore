package com.nawaz.controller;

import java.net.HttpURLConnection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nawaz.entity.Ratings;
import com.nawaz.model.RatingsDto;
import com.nawaz.model.ResponseMessage;
import com.nawaz.service.RatingsService;
import com.nawaz.utility.Constants;

@RestController
@RequestMapping("/rating-api")
public class RatingController {
	
	
	@Autowired
	private RatingsService ratingService;
	

	@PostMapping("/ratingbooks")
	public ResponseEntity<ResponseMessage> rateBooks(@RequestBody RatingsDto ratingsDto) {
		try {
		Ratings ratingRivews = ratingService.createRatingReviews(ratingsDto);
		if(ratingRivews!=null) {
	       return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_CREATED, Constants.SUCCESS, "rating save successfully", ratingRivews));
		} else {
			 return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED, "rating  save Failed", ratingRivews));
 
	       }

		 }catch (Exception e) {
			 return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_INTERNAL_ERROR, Constants.FAILED, "Internal server error"));
		}
	}

	

    @GetMapping("/getAllreviews")
	public ResponseEntity<ResponseMessage> getAllreview() {
	   
		List<Ratings> byAllReview = ratingService.getByAllReviews();
		if(byAllReview!=null) {
	       return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_OK, Constants.SUCCESS, "custmer  getting all reviews successfully", byAllReview));
		
		 }else {
			 return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED, "custmer  getting all reviews Failed", byAllReview));

		 }
	}  
	

}
