package com.nareshit.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.nawaz.entity.Customer;
import com.nawaz.repository.CustomerRepo;
import com.nawaz.serviceImpl.customerServiceImpl;

@SpringBootTest
public class CustomerServiceImplTest {
	
	@Mock
	private CustomerRepo customerRepo;
	
	@InjectMocks
	customerServiceImpl customerService;
	
	private Customer customer;
    private Customer savedCustomer;

    @BeforeEach
    void setUp() {
        customer = new Customer();
        customer.setName("John Doe");
        customer.setEmail("john.doe@example.com");
        customer.setContactNo(1234567890L);

        savedCustomer = new Customer();
        savedCustomer.setId(1);
        savedCustomer.setName("John Doe");
        savedCustomer.setEmail("john.doe@example.com");
        savedCustomer.setContactNo(1234567890L);
        
        
    }
    
    @Test
    void testStoreCustomer_Success() {
        // Arrange
        when(customerRepo.save(any(Customer.class))).thenReturn(savedCustomer);

        // Act
        Customer result = customerService.storeCustomer(customer);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("John Doe", result.getName());
        verify(customerRepo, times(1)).save(customer);
    }
    
    @Test
    void testUpdateCustomer_Success() {
        // Arrange
        Customer updatedCustomer = new Customer();
        updatedCustomer.setId(1);
        updatedCustomer.setName("John Updated");
        updatedCustomer.setEmail("john.updated@example.com");
        updatedCustomer.setContactNo(987654321L);

        when(customerRepo.save(any(Customer.class))).thenReturn(updatedCustomer);

        // Act
        Customer result = customerService.updateCustomer(updatedCustomer);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("John Updated", result.getName());
        verify(customerRepo, times(1)).save(updatedCustomer);
    }

}




