package com.nawaz.serviceImpl;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.nawaz.entity.BooksExcelFile;
import com.nawaz.repository.BooksExcelFileRepo;
import com.nawaz.service.BooksExcelUploadService;
import com.nawaz.utility.Helper;


@Service
public class BooksExcelIntoDBServiceImpl implements BooksExcelUploadService {

	@Autowired
	BooksExcelFileRepo fileRepo;
	
	@Override
	public void uploadExcelIntoDB(MultipartFile file) throws IOException {
		
		// FIX: Actually process the Excel file and save to database
        try (InputStream inputStream = file.getInputStream()) {
            // Process Excel and get list of books
            List<BooksExcelFile> books = Helper.excelFileInsertDatabase(inputStream);
            
            // Save all books to database
            if (books != null && !books.isEmpty()) {
                fileRepo.saveAll(books);
                System.out.println("Saved " + books.size() + " books to database");
            } else {
                System.out.println("No books found in Excel file");
            }
        } catch (Exception e) {
            System.err.println("Error processing Excel file: " + e.getMessage());
            e.printStackTrace();
            throw e; // Re-throw to see error in response
        }
		

	}

}
