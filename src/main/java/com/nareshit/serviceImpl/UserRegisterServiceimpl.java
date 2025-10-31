package com.nareshit.serviceImpl;

import java.util.Base64;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nareshit.entity.UserRegister;
import com.nareshit.entity.mongo.UserRegisterMongo;
import com.nareshit.model.UserRequestDto;
import com.nareshit.repository.UserRegisterRepo;
import com.nareshit.repository.momgo.UserRegisterRepositoryMongo;
import com.nareshit.service.UserRegisterService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserRegisterServiceimpl implements UserRegisterService {
	
	@Autowired
	private UserRegisterRepo userRegisterRepo;
	
	@Autowired
	private UserRegisterRepositoryMongo userrepoMongo;

	@Override
	public UserRegister insertUserRegister(UserRequestDto userRequestDto) {
		
		log.info("Registration serive layer calling or started");
		UserRegister user =new UserRegister();
		try {
		user.setFirstName(userRequestDto.getFirstName());
		user.setLastName(userRequestDto.getLastName());
		user.setEmail(userRequestDto.getEmail());
		user.setPassword(Base64.getEncoder().encodeToString(userRequestDto.getPassword().getBytes()));
		user.setContactId(userRequestDto.getContactId());
		userRegisterRepo.save(user);
		
		UserRegisterMongo userMong = new UserRegisterMongo();
		userMong.setFirstName(userRequestDto.getFirstName());
		userMong.setLastName(userRequestDto.getLastName());
		userMong.setEmail(userRequestDto.getEmail());
		userMong.setPassword(Base64.getEncoder().encodeToString(userRequestDto.getPassword().getBytes()));
		userMong.setContactId(userRequestDto.getContactId());
		userrepoMongo.save(userMong);
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