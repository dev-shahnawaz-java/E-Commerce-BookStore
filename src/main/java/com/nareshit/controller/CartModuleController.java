package com.nareshit.controller;

import java.net.HttpURLConnection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nareshit.entity.CartModule;
import com.nareshit.model.ResponseMessage;
import com.nareshit.utility.Constants;

@RestController
public class CartModuleController {
	
	@Autowired
	private CartModuleService cartModuleService;
	
	
	@PostMapping("/addtocart")
	public ResponseEntity<ResponseMessage> addToCarts(@RequestParam Long customerId,
			                                          @RequestParam Long bookId,
			                                          @RequestParam int quantity){
		
		try {
			CartModule cartBooks = cartModuleService.addToCartBooks(customerId, bookId, quantity);
			
			if(cartBooks != null) {
				return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_CREATED,Constants.SUCCESS,"Added the cart into books successfully", cartBooks));
				
			}else {
				return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED, "Added the cartare failure", cartBooks));
			}
			
		}catch(Exception e) {
			return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_BAD_GATEWAY, Constants.FAILURE, "Added the cart Books getting failed", e.getMessage()));
			
		}
	}

}
