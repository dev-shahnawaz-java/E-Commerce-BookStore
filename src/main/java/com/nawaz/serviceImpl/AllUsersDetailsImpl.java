package com.nawaz.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.nawaz.entity.UserRegister;
import com.nawaz.model.UserRequestDto;
import com.nawaz.repository.AllUsersDetailsRepo;
import com.nawaz.service.AllUsersDetails;

@Service
public class AllUsersDetailsImpl implements AllUsersDetails{
	
	@Autowired
	private AllUsersDetailsRepo allUsersDetailsRepo;
	

	@Cacheable(value="get all users")
	@Override
	public List<UserRequestDto> getAllUsers() {
		
		
		List<UserRegister> users = allUsersDetailsRepo.findAll();
		
		System.out.println("data base hitted");
		   List<UserRequestDto> userDto = new ArrayList<>();
		
		for(UserRegister user: users) {
			
			UserRequestDto dto = new UserRequestDto();
			dto.setFirstName(user.getFirstName());
			dto.setLastName(user.getLastName());
			dto.setContactId(user.getContactId());
			dto.setEmail(user.getEmail());
			dto.setPassword(user.getPassword());
			
			userDto.add(dto);
						
		}
		
	        return userDto;
	}
	
	
	
	
	

}
