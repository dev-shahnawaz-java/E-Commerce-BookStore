package com.nareshit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nareshit.entity.UserRegister;


@Repository  //it is a optional
public interface UserRegisterRepo extends JpaRepository<UserRegister, Long>{

	
	
}
