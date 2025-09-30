package com.nareshit.serviceImpl;

import java.util.Base64;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nareshit.entity.UserRegister;
import com.nareshit.model.UserRequestDto;
import com.nareshit.repository.UserRegisterRepo;
import com.nareshit.service.UserRegisterService;

@Service
public class UserRegisterServiceimpl implements UserRegisterService {
	
	@Autowired
	private UserRegisterRepo userRegisterRepo;

	@Override
	public UserRegister insertUserRegister(UserRequestDto userRequestDto) {
		UserRegister user =new UserRegister();
		try {
		user.setFirstName(userRequestDto.getFirstName());
		user.setLastName(userRequestDto.getLastName());
		user.setEmail(userRequestDto.getEmail());
		user.setPassword(Base64.getEncoder().encodeToString(userRequestDto.getPassword().getBytes()));
		user.setContactId(userRequestDto.getContactId());
		userRegisterRepo.save(user);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public UserRegister getUserRegisterDetails(Long id) {
		
		Optional<UserRegister> byId = userRegisterRepo.findById(id);
		UserRegister userRegister = byId.get();
		
		
		return new UserRegister(userRegister.getFirstName(), userRegister.getLastName());
	}

}