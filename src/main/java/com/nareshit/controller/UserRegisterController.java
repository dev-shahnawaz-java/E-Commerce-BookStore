package com.nareshit.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nareshit.entity.UserRegister;
import com.nareshit.service.UserRegisterService;


@RestController
public class UserRegisterController {
	
	@Autowired
	private UserRegisterService userRegisterService;
	
	@PostMapping("/userregisters")
	public String createUserRegister(@RequestBody UserRegister userRegister) {
		
		userRegisterService.insertUserRegister(userRegister);
		
		
		return "User Register saved successfully";
		
	}
	
	
	
	
	
	

}
