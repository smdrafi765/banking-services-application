package com.rafi.banking.services.application.service;

import com.rafi.banking.services.application.entity.Customer;
import com.rafi.banking.services.application.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private  final CustomerRepository customerRepository;


    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    //Create new Customer
    public Customer createCustomer(Customer customer)
    {
         return customerRepository.save(customer);
    }
    
    //get all Customers 
    public List<Customer> getAllCustomers()
    {
        return customerRepository.findAll();
    }
    
    
    //get Customer by Id
    public Optional<Customer> getCustomerById(Long id)
    {
        return  customerRepository.findById(id);
    }
    
    //Update Customer Detail
    public Customer updateCustomer(Customer customer, Long id)
    {
    	
    	 Customer existingCustomer= customerRepository.findById(id).orElseThrow(()->new RuntimeException("User not Found"));
    	
    	
    	if(customer.getName()!=null)
    		existingCustomer.setName(customer.getName());
    	
    	if(customer.getPhone()!=null)
    		existingCustomer.setPhone(customer.getPhone());
    	
    	if(customer.getEmail()!=null)
    		existingCustomer.setEmail(customer.getEmail());
    	
    	return customerRepository.save(existingCustomer);
    	
    }
    
    // Delete the Customer
    public void deleteCustomer(Long id)
    {
    	
    	 Customer existingCustomer= customerRepository.findById(id).orElseThrow(()->new RuntimeException("user not found"));
    	 
    	 if(existingCustomer.getAccounts()!=null&& !existingCustomer.getAccounts().isEmpty())
    	 {
    		 
    		 throw new RuntimeException("Cannot Delete the Customer Accounts Exists");
    		 
    	 }
    	 
    	 customerRepository.delete(existingCustomer);
    	
    	
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
