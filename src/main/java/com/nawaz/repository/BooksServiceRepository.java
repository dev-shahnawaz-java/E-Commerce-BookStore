package com.nawaz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nawaz.entity.BooksModule;

@Repository
public interface BooksServiceRepository extends JpaRepository<BooksModule, Long> {

}
