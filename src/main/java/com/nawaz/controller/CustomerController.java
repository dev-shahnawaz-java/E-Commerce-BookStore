package com.nawaz.controller;

import java.net.HttpURLConnection;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nawaz.entity.Customer;
import com.nawaz.exceptions.CustomerNotFoundException;
import com.nawaz.model.CustomerDto;
import com.nawaz.model.ResponseMessage;
import com.nawaz.serviceImpl.customerServiceImpl;
import com.nawaz.utility.Constants;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "SaveCustomer", description = "Saving cutomer details")
public class CustomerController {
	
	@Autowired
	private customerServiceImpl service;
	
	 @Operation(summary = "Create customer", description = "Create a new customer in the system")
	    @ApiResponses(value = {
	        @ApiResponse(responseCode = "201", description = "Customer created successfully"),
	        @ApiResponse(responseCode = "400", description = "Invalid input"),
	        @ApiResponse(responseCode = "500", description = "Internal server error")
	    })
	@PostMapping("/customersave")
	public ResponseEntity<ResponseMessage> createCustomer(@RequestBody Customer customer){
		 
		 try {
			 if(customer.getEmail() == null || customer.getEmail().isEmpty() || customer.getName() == null || customer.getName().isEmpty()) {
				 
				 return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED, "email or name cannot be empty"));	 
			 }
			 
			 Customer insertedCustomer = service.storeCustomer(customer);
			 
			 if(insertedCustomer != null) {
				 return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_CREATED, Constants.SUCCESS, "Cutomer saved successfully" ,insertedCustomer));
			 }else {
				 
				 return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED, "Ctomer does not save " ,insertedCustomer));
			 }
		 }catch(Exception e) {
			 
			 return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_INTERNAL_ERROR, Constants.FAILED, "Ctomer does not save " ));
			
		 }
		
		
	}
	 
	 @PostMapping("/customerupdate")
	 public ResponseEntity<ResponseMessage> updateCustomer(@RequestBody Customer customer){
			 
			 
				 if(customer.getEmail() == null || customer.getEmail().isEmpty() || customer.getName() == null || customer.getName().isEmpty()) {
					 
					 return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED, "email or name cannot be empty"));	 
				 }
				 
				 if(customer.getId() == null) {
					 
				 Customer insertedCustomer = service.updateCustomer(customer); 
				 return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_CREATED, Constants.SUCCESS, "Customer updated successfully" ,insertedCustomer));
				 }else{
					 
					 Customer insertCustomer = service.storeCustomer(customer);
					 
					 return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_CREATED, Constants.SUCCESS, "Customer updated successfully" ,insertCustomer));
				 }

					 
		}
	 
	 
	 @PostMapping("/createOrUpdate")
	 public ResponseEntity<ResponseMessage> createOrUpdate(@RequestBody Customer customer){
		 
		 try {
			 if(customer.getEmail() == null || customer.getEmail().isEmpty() || customer.getName() == null || customer.getName().isEmpty()) {
				 
				 return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED, "email or name cannot be empty"));	 
			     }
			 
			 if(customer.getId() == null) {
				 Customer createOrUpdate = service.saveOrUpdateCustomer(customer);
				 return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_CREATED, Constants.SUCCESS, "Customer updated successfully" ,createOrUpdate));
				 
			 }else {
				 Customer createOrUpdate = service.saveOrUpdateCustomer(customer);
				 return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_OK, Constants.SUCCESS, "Customer updated successfully" ,createOrUpdate));
			 

			 }
				 
			 }catch(Exception e) {
				 return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_INTERNAL_ERROR, Constants.FAILED, "Ctomer does not save " ));
								 
			 }
	
	 }
	 
	
	 @GetMapping("/getcustomer/{id}")
	 public ResponseEntity<ResponseMessage> fetchCustomerById(@PathVariable Integer id){
		 
	
	            Customer cust = service.getByCustomerByid(id);
	            
	            if(cust != null) {
	            	return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_OK, Constants.SUCCESS, "custmer id getting successfully", cust));
	            }else {
	            	 return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED, "custmer id getting Failed", cust));

	            }           
		 
	 }
	 
	
	 @GetMapping("/getallcustomers")
	 public ResponseEntity<ResponseMessage> fetchAllCustomers(){
		 try {
	            List<Customer> allCustomers = service.getAllCustomers();
	            List<CustomerDto> allCustDto = new ArrayList<>();
	            
	            for (Customer cust : allCustomers) {
	                CustomerDto custDto = new CustomerDto();
	                BeanUtils.copyProperties(cust, custDto); // Fixed: was copying wrong object
	                allCustDto.add(custDto);
	            }
	            
	            ResponseMessage response = new ResponseMessage(200, "success", "Customers fetched successfully", allCustDto);
	            return ResponseEntity.ok(response);
	            
	        } catch (CustomerNotFoundException e) {
	            // This will be handled by GlobalExceptionHandler
	            throw e;
	        }
		 
		 
	 }
	 
	 @GetMapping("/getAllCustmerswithpagination")
		public ResponseEntity<ResponseMessage> getByAllCustmerpagination(@RequestParam int page , 
				                                                         @RequestParam int size,
				                                                         @RequestParam String sortField,
				                                                         @RequestParam String pageDir) {
	    
	    Page<Customer> byAllCustmersWithPaginations = service.getByAllCustmersWithPaginations(page,size,sortField,pageDir);
	     	    	 
	     if(byAllCustmersWithPaginations!=null) {
			
	     	  return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_OK, Constants.SUCCESS, "All Custemers getting with pagination successfully", byAllCustmersWithPaginations));
		    }else { 
		    	
		    	return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED, "All Custemers getting  pagination Failure", byAllCustmersWithPaginations));
		    	
		    }}
	 
	
}

























