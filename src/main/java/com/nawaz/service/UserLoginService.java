package com.nawaz.service;

import com.nawaz.entity.UserRegister;
import com.nawaz.model.UserLoginDto;

public interface UserLoginService {
	
	public UserRegister authenticateUser(UserLoginDto userLoginDto);

}
