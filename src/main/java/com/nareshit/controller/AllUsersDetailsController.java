package com.nareshit.controller;

import java.net.HttpURLConnection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nareshit.model.ResponseMessage;
import com.nareshit.model.UserRequestDto;
import com.nareshit.service.AllUsersDetails;
import com.nareshit.utility.Constants;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;


@Tag(name = "UserRegisterController ",description = "UserRegister Regsiter and Login")

@RestController
public class AllUsersDetailsController {
	
	@Autowired
	private AllUsersDetails allUsersDetails;
	
	
	@Operation(summary = "Create User Register",description = "e commerece online books store  register the users")
    @ApiResponses({
     @ApiResponse(responseCode = "201",description = "user register successfully"),
     @ApiResponse(responseCode = "400",description = "user register failure"),
     @ApiResponse(responseCode = "500",description = "Internal server error")
     })
	@GetMapping("/getall")
	public ResponseEntity<ResponseMessage> showAllUsersDetails(){
		
		List<UserRequestDto> allUsers = allUsersDetails.getAllUsers();
		
		return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessage(HttpURLConnection.HTTP_CREATED, Constants.SUCCESS, "All Users Details " ,allUsers));
		
	}
	
	

}
