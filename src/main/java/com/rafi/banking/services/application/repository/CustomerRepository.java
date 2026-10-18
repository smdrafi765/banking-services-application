package com.rafi.banking.services.application.repository;

import com.rafi.banking.services.application.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
}
