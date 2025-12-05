package com.nawaz.service;

import java.util.List;

import com.nawaz.entity.Ratings;
import com.nawaz.model.RatingsDto;

public interface RatingsService {
	
	public Ratings createRatingReviews(RatingsDto ratingDto);
	
	public List<Ratings> getByAllReviews();

}
