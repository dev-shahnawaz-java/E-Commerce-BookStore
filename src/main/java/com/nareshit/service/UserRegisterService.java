package com.nareshit.service;

import com.nareshit.entity.UserRegister;
import com.nareshit.model.UserRequestDto;

public interface UserRegisterService {

	public UserRegister  insertUserRegister(UserRequestDto userRequestDto);
	
	public UserRegister getUserRegisterDetails(Long id);
	

}
