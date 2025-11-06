package com.nawaz.controller;

import java.io.IOException;
import java.net.HttpURLConnection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.nawaz.model.ResponseMessage;
import com.nawaz.service.BooksExcelUploadService;
import com.nawaz.utility.Constants;
import com.nawaz.utility.Helper;

@RestController
public class BooksExcelUploadController {
	
	@Autowired
	BooksExcelUploadService uploadService;
	
	@PostMapping("/uploadExcelFile")
	public ResponseEntity<ResponseMessage> postMethodName(@RequestParam MultipartFile file) throws IOException {
	
		if(Helper.checkExcelFile(file)) {
			uploadService.uploadExcelIntoDB(file);
			return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_OK, Constants.SUCCESS, "Excelfile save successfully"));
		
	}else {
		
		return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILURE, "Excelfile save failed"));

	}
	
}

}
