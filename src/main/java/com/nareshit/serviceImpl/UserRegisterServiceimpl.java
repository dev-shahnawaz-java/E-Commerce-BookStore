package com.nareshit.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nareshit.entity.UserRegister;
import com.nareshit.repository.UserRegisterRepo;
import com.nareshit.service.UserRegisterService;

@Service
public class UserRegisterServiceimpl implements UserRegisterService {
	
	
	@Autowired
	private UserRegisterRepo userRegisterRepo;

	@Override
	public String insertUserRegister(UserRegister userRegister) {
		
		userRegisterRepo.save(userRegister);
		
		return "save successfully";
	}

}
