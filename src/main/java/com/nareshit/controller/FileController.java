package com.nareshit.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.nareshit.entity.FileEntity;
import com.nareshit.repository.FileRepo;

@RestController
public class FileController {
	
	@Autowired
	private FileRepo fileRepo;
	
	
	@PostMapping("/uploadfiles")
	public ResponseEntity<String> uploadFile(@RequestParam MultipartFile file)
	              throws IOException{
		
		FileEntity fss = new FileEntity();
		fss.setFileName(file.getOriginalFilename());
		fss.setFileType(file.getContentType());
		fss.setData(file.getBytes());
		
		fileRepo.save(fss);
		
		
		return ResponseEntity.ok("file inserted successfully: " + file.getOriginalFilename());
		
	}
	
	public ResponseEntity<List<Object>> uploadMultiFile(@RequestParam MultipartFile[] files)
	                                    throws IOException{
		
		List<Object> response = Arrays.stream(files).map(s->{
			
			try {
				return uploadFile(s);
			}catch(Exception e) {
				return "files upload failed "+ e.getLocalizedMessage();
			}
		}).collect(Collectors.toList());
		
		return ResponseEntity.ok(response);
		
	}

}





























