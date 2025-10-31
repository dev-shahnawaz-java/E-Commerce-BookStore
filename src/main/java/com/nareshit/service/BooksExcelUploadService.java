package com.nareshit.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface BooksExcelUploadService {
	
	public void uploadExcelIntoDB(MultipartFile file) throws IOException;

}
