package com.nareshit.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Base64;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.nareshit.entity.UserRegister;
import com.nareshit.model.UserRequestDto;
import com.nareshit.repository.UserRegisterRepo;
import com.nareshit.serviceImpl.UserRegisterServiceimpl;

@SpringBootTest
public class UserRegisterServiceTest {
	@MockBean
	private UserRegisterRepo userRegisterRepo;//fake db
	
	@Autowired
	private UserRegisterServiceimpl userRegisterService;
	
	@Test
	public void testInsertUserRegister() {
		
		//step1: create input data 
		UserRequestDto input = new UserRequestDto();
		input.setFirstName("kamal");
		input.setLastName("hasan");
		input.setEmail("kamal@gmial.com");
		input.setPassword("hasankamal");
		
		
		//Create Fake Db output (as if user saved in db)
		
		UserRegister savedUser = new UserRegister();
		savedUser.setFirstName("Shrinu");
		savedUser.setLastName("Yadab");
		savedUser.setEmail("shrinu@yadav.com");
		
		savedUser.setPassword(Base64.getEncoder().encodeToString("hasankamal".getBytes()));
		
		when(userRegisterRepo.save(any(UserRegister.class))).thenReturn(savedUser);
		
		//call actual service method 
		UserRegister result = userRegisterService.insertUserRegister(input);
		
		//check or verify output
		
		assertNotNull(result);//result should not be null
		assertEquals("kamal", result.getFirstName());
		assertEquals("kamal@gmial.com", result.getEmail());
		
		//save() called only once 
		verify(userRegisterRepo, times(1)).save(any(UserRegister.class));
		
		
		
	}
	

}
