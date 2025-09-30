package com.nareshit.serviceImpl;

import java.util.Base64;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nareshit.entity.UserRegister;
import com.nareshit.model.UserLoginDto;
import com.nareshit.repository.UserRegisterRepo;
import com.nareshit.service.UserLoginService;

@Component
public class UserLoginServiceImpl implements UserLoginService {

	
	@Autowired
	private UserRegisterRepo userRegisterRepo;
	

	@Override
	public UserRegister authenticateUser(UserLoginDto userLoginDto) {
		
		try {
			//find user by email
			
			Optional<UserRegister> userOptional = userRegisterRepo
					                              .findByEmail(userLoginDto.getEmail());
			
			if(userOptional.isPresent()) {
				UserRegister user = userOptional.get();
				
				//decode the stored password and compare
				
				String storedPassword = new String(Base64.getDecoder().decode(user.getPassword()));
				
				String inputPassword = userLoginDto.getPassword();
				
				if(storedPassword.equals(inputPassword)) {
					return user;// Authentication successful
				}
				
			}
			
			return null; // Authentication failed
			
		}catch(Exception e) {
			
			return null;
		}
		
	}

	
}
