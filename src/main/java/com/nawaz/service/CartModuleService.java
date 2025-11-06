package com.nawaz.service;

import com.nawaz.entity.CartModule;

public interface CartModuleService {
	
	public CartModule addToCartBooks(Integer customerId, Long bookId, int quantity);

	
	

}
