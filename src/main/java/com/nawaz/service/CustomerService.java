package com.nawaz.service;

import java.util.List;


import org.springframework.data.domain.Page;

import com.nawaz.entity.Customer;

public interface CustomerService {
	
	public Customer storeCustomer(Customer customer);
	public Customer updateCustomer(Customer customer);
	public Customer saveOrUpdateCustomer(Customer customer) throws Exception;
	public Customer getByCustomerByid(Integer id);
	public List<Customer> getAllCustomers();
	//public Customer getByCustomerid(Integer id);
	
	public Page<Customer> getByAllCustmersWithPaginations(int page, int size, 
			String sortField, String pageDir);

}
