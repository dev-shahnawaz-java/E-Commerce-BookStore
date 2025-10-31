package com.nareshit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nareshit.entity.BooksExcelFile;

public interface BooksExcelFileRepo extends JpaRepository<BooksExcelFile, Long> {

}
