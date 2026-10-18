package com.rafi.banking.services.application.repository;

import com.rafi.banking.services.application.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
