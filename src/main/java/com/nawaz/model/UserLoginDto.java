package com.nawaz.model;

import org.springframework.stereotype.Component;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Component
public class UserLoginDto {
	
	 @NotBlank(message = "Email cannot be blank")
	    @Schema(description = "User email", example = "user@example.com")
	    private String email;
	    
	    @NotBlank(message = "Password cannot be blank")
	    @Schema(description = "User password", example = "password123")
	    private String password;

	    // Constructors
	    public UserLoginDto() {}
	    
	    public UserLoginDto(String email, String password) {
	        this.email = email;
	        this.password = password;
	    }
	    
	    // Getters and Setters
	    public String getEmail() {
	        return email;
	    }
	    
	    public void setEmail(String email) {
	        this.email = email;
	    }
	    
	    public String getPassword() {
	        return password;
	    }
	    
	    public void setPassword(String password) {
	        this.password = password;
	    }

}
