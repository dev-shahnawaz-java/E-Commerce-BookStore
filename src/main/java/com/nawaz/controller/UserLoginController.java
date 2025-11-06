package com.nawaz.controller;


import java.net.HttpURLConnection;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nawaz.entity.UserRegister;
import com.nawaz.model.ResponseMessage;
import com.nawaz.model.UserLoginDto;
import com.nawaz.service.UserLoginService;
import com.nawaz.utility.Constants;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "UserLoginController", description = "User Login Authentication using existing UserRegister data")
@RestController
public class UserLoginController {

	@Autowired
	private UserLoginService userLoginService;
	
	 @Operation(summary = "User Login", description = "Authenticate user using email and password against registered users")
	    @ApiResponses({
	        @ApiResponse(responseCode = "200", description = "Login successful"),
	        @ApiResponse(responseCode = "401", description = "Invalid credentials"),
	        @ApiResponse(responseCode = "400", description = "Bad request"),
	        @ApiResponse(responseCode = "500", description = "Internal server error")
	    })
	    @PostMapping("/login")
	public ResponseEntity<ResponseMessage> loginUser(@RequestBody UserLoginDto userLoginDto){
		
		 try {
	            // Validate input parameters
	            if (userLoginDto.getEmail() == null || userLoginDto.getEmail().isEmpty()) {
	            	
	                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                    .body(new ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST,
	                    		Constants.FAILED, "Email cannot be empty"));
	            }
	            
	            if (userLoginDto.getPassword() == null || userLoginDto.getPassword().isEmpty()) {
	                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                    .body(new ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED, "Password cannot be empty"));
	            }
	            
	            // Authenticate user against existing UserRegister data
	            UserRegister authenticatedUser = userLoginService.authenticateUser(userLoginDto);
	            
	            if (authenticatedUser != null) {
	                // Login successful - return the complete user data like registration API
	                return ResponseEntity.status(HttpStatus.OK)
	                    .body(new ResponseMessage(
	                        HttpURLConnection.HTTP_OK, 
	                        Constants.SUCCESS, 
	                        "User login successful", 
	                        authenticatedUser
	                    ));
	            } else {
	                // Login failed - invalid credentials
	                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
	                    .body(new ResponseMessage(
	                        HttpURLConnection.HTTP_UNAUTHORIZED, 
	                        Constants.FAILED, 
	                        "Invalid email or password"
	                    ));
	            }
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body(new ResponseMessage(
	                    HttpURLConnection.HTTP_INTERNAL_ERROR, 
	                    Constants.FAILED, 
	                    "Internal server error: " + e.getMessage()
	                ));
	        }
	}
}
