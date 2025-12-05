package com.nawaz.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nawaz.entity.Ratings;

public interface RatingsRepo extends JpaRepository<Ratings, Long> {

}
