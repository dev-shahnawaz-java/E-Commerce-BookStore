package com.nareshit.service;

import com.nareshit.entity.UserRegister;
import com.nareshit.model.UserLoginDto;

public interface UserLoginService {
	
	public UserRegister authenticateUser(UserLoginDto userLoginDto);

}
