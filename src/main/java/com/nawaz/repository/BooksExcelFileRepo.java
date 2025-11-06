package com.nawaz.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nawaz.entity.BooksExcelFile;

public interface BooksExcelFileRepo extends JpaRepository<BooksExcelFile, Long> {

}
