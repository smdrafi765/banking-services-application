
package com.rafi.banking.services.application.controller;

import com.rafi.banking.services.application.entity.Customer;
import com.rafi.banking.services.application.service.CustomerService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    // CREATE NEW CUSTOMER
    @PostMapping
    public ResponseEntity<Customer> createCustomer(
            @RequestBody Customer customer) {

        Customer savedCustomer = customerService.createCustomer(customer);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedCustomer);
    }

    // GET ALL CUSTOMERS
    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {

        List<Customer> customers = customerService.getAllCustomers();

        return ResponseEntity.ok(customers);
    }

    // GET CUSTOMER BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(
            @PathVariable Long id) {

        Customer customer = customerService.getCustomerById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Customer not found"));

        return ResponseEntity.ok(customer);
    }

    // UPDATE CUSTOMER 
    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(
            @RequestBody Customer customer,
            @PathVariable Long id) {

        Customer updatedCustomer =
                customerService.updateCustomer(customer, id);

        return ResponseEntity.ok(updatedCustomer);
    }
    
    
    //DELETE THE CUSTOMER
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable long id)
    {
    	
    	customerService.deleteCustomer(id);
    	
    	return ResponseEntity.noContent().build();
    	
    	
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
