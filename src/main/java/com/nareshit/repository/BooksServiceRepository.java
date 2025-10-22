package com.nareshit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nareshit.entity.BooksModule;

@Repository
public interface BooksServiceRepository extends JpaRepository<BooksModule, Long> {

}
