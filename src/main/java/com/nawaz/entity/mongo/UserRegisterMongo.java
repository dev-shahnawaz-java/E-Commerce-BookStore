package com.nawaz.entity.mongo;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection="user_register")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterMongo {
	
	@Id
	private String id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private Long contactId;
	

}
