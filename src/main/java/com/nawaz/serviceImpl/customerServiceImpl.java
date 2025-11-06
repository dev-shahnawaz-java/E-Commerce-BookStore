package com.nawaz.serviceImpl;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.nawaz.entity.Customer;
import com.nawaz.exceptions.CustomerNotFoundException;
import com.nawaz.repository.CustomerRepo;
import com.nawaz.service.CustomerService;

@Service
public class customerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerRepo customerRepo;

	@Override
	public Customer storeCustomer(Customer customer) {
		
	  Customer savedCust = customerRepo.save(customer);
		
		return savedCust;
	}

	@Override
	public Customer updateCustomer(Customer customer) {
        Customer updateCust = customerRepo.save(customer);
		
		return updateCust;
	}

	@Override
	public Customer saveOrUpdateCustomer(Customer customer) {
		
		if(customer.getId() == null){
			Customer insert = customerRepo.save(customer);
		}else {
			
			Optional<Customer> cust = customerRepo.findById(customer.getId());
			if(cust.isPresent()) {
				Customer existCust = cust.get();
				existCust.setName(customer.getName());
				existCust.setEmail(customer.getEmail());
				existCust.setContactNo(customer.getContactNo());
				
				
				customerRepo.save(existCust);
			}else {
				throw new RuntimeException("Customer not found");
			}
			
		}
		return customer;
	}

	@Override
	public Customer getByCustomerByid(Integer id){
		 Optional<Customer> customer = customerRepo.findById(id);
		 if(!customer.isPresent()) {
				
				throw new CustomerNotFoundException("Custmer Id Not Found");
			}
			return customer.get();
		}

	@Override
	public List<Customer> getAllCustomers() {
		List<Customer>  allCustomers = customerRepo.findAll();
		
		return allCustomers;
	}

	@Override
	public Page<Customer> getByAllCustmersWithPaginations(int page, int size, String sortField, String pageDir) {
		
		Sort sort = pageDir.equalsIgnoreCase("asc") ? Sort.by(sortField).ascending() :
			 Sort.by(sortField).descending();
		
		PageRequest request = PageRequest.of(page, size, sort);
				
		return customerRepo.findAll(request);
	}

	
	

	

	

}
