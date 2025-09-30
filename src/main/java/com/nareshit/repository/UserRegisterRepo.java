package com.nareshit.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nareshit.entity.UserRegister;


@Repository  //it is a optional
public interface UserRegisterRepo extends JpaRepository<UserRegister, Long>{

	@Query("SELECT u FROM UserRegister u WHERE u.email = :email")
    Optional<UserRegister> findByEmail(@Param("email") String email);
	
	
}
