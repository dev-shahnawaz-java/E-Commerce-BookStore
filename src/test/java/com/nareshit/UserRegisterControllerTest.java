package com.nareshit;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nawaz.controller.UserRegisterController;
import com.nawaz.entity.UserRegister;
import com.nawaz.model.UserRequestDto;
import com.nawaz.service.UserRegisterService;

@WebMvcTest(UserRegisterController.class) // for which class is test for
public class UserRegisterControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserRegisterService userRegService;

	@Test
	public void testUserRegister() throws Exception {

		// 1 input data
		UserRequestDto userRequest = new UserRequestDto();
		userRequest.setEmail("satyam@gmail.com");
		userRequest.setPassword("password");

		// 2 mock service response
		UserRegister mockResponse = new UserRegister();
		mockResponse.setId(1L);
		mockResponse.setEmail("test@gmail.com");

		// 3. Mock service call

		when(userRegService.insertUserRegister(userRequest)).thenReturn(mockResponse);

		// 4.perform post and validate response

		mockMvc.perform(post("/userregisters").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(userRequest)))
				.andExpect(status().isCreated())
				.andExpect((ResultMatcher) content().contentType(MediaType.APPLICATION_JSON));

	}
	
	

}




























