package com.rafi.banking.services.application.service;

import com.rafi.banking.services.application.dto.AccountRequest;
import com.rafi.banking.services.application.entity.Account;
import com.rafi.banking.services.application.entity.Customer;
import com.rafi.banking.services.application.repository.AccountRepository;
import com.rafi.banking.services.application.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;


    public AccountService(AccountRepository accountRepository, CustomerRepository customerRepository) {
        this.accountRepository = accountRepository;
        this.customerRepository = customerRepository;
    }

     public Optional<Account> createAccount(AccountRequest request)
     {
         Optional<Customer> customerOptional= customerRepository.findById(request.getCustomerId());
         if(customerOptional.isEmpty())
         {
             return Optional.empty();
         }
         Customer customer= customerOptional.get();
         Account account=new Account();
         account.setAccountNumber(request.getAccountNumber());
         account.setBalance(request.getBalance());
         account.setAccountType(request.getAccountType());
         account.setCustomer(customer);
       return   Optional.of(accountRepository.save(account)); //saved account in database by jpa
     }

     public List<Account> getAllAccounts()
     {
         return accountRepository.findAll();
     }

     public Optional<Account> getAccountById(Long id)
     {
         return accountRepository.findById(id);
     }


     //account update by Id
    public Account updateAccount(Account updatedAccount, Long id) {

        Account existingAccount = accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        existingAccount.setAccountType(updatedAccount.getAccountType());

        return accountRepository.save(existingAccount);
    }


    public void deleteAccountById(Long id)
     {
    	 
    	 Optional<Account> acOptional= accountRepository.findById(id);
    	 Account account= acOptional.orElseThrow(()->new RuntimeException("Account Not Found"));
    	 
    	 //delete the account
    	 accountRepository.delete(account);
    			 
     }



}
