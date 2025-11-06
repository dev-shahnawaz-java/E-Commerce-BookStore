package com.nareshit;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nawaz.controller.AllUsersDetailsController;
import com.nawaz.model.UserRequestDto;
import com.nawaz.service.AllUsersDetails;

//@WebMvcTest(AllUsersDetailsController.class)
public class AllUsersDetailsControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private AllUsersDetails allUsersDetail;

	@Test
	public void testAllUsersDetail() throws JsonProcessingException, Exception{
		
		//Input request
		UserRequestDto ur1 = new UserRequestDto();
		ur1.setFirstName("Rajib");
		ur1.setLastName("maharana");
		ur1.setEmail("rajib@gmail.com");
		
		
		//Mock Response 
		UserRequestDto ur2 = new UserRequestDto();
		ur1.setFirstName("Hello");
		ur1.setLastName("Yaro");
		ur1.setEmail("xyz@gmail.com");
		
		
		List<UserRequestDto> list = Arrays.asList(ur1,ur2);
		
		when(allUsersDetail.getAllUsers()).thenReturn(list);
		
		mockMvc.perform(get("/getall").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
		.andExpect((ResultMatcher) content().contentType(MediaType.APPLICATION_JSON))
		.andExpect((ResultMatcher) content().json(new ObjectMapper().writeValueAsString(list)));
		
		
		
	}
}
