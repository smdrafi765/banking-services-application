package com.rafi.banking.services.application.repository;

import com.rafi.banking.services.application.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,Long> {




}
