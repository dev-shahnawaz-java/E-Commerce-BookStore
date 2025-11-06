package com.nawaz.serviceImpl;

import java.util.Base64;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nawaz.entity.UserRegister;
import com.nawaz.model.UserLoginDto;
import com.nawaz.repository.UserRegisterRepo;
import com.nawaz.service.UserLoginService;

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
