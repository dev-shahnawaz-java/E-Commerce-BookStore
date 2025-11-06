package com.nawaz.service;

import com.nawaz.entity.UserRegister;
import com.nawaz.model.UserRequestDto;

public interface UserRegisterService {

	public UserRegister  insertUserRegister(UserRequestDto userRequestDto);
	
	public UserRegister getUserRegisterDetails(Long id);
	

}
