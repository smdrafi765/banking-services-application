package com.rafi.banking.services.application.controller;

import com.rafi.banking.services.application.dto.AccountRequest;
import com.rafi.banking.services.application.dto.TransactionResponse;
import com.rafi.banking.services.application.entity.Account;
import com.rafi.banking.services.application.entity.Transaction;
import com.rafi.banking.services.application.service.AccountService;
import com.rafi.banking.services.application.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {


    private final AccountService accountService;
    private final TransactionService transactionService;


    public AccountController(AccountService accountService, TransactionService transactionService) {
        this.accountService = accountService;
        this.transactionService = transactionService;
    }

    // CREATE NEW ACCOUNT
    @PostMapping
    public Account createAccount(@RequestBody AccountRequest request) {
        return accountService.createAccount(request).orElse(null);
    }

    //GET ALL ACCOUNTS
    @GetMapping
    public List<Account> getAllAccounts() {
        return accountService.getAllAccounts();
    }


    // GET ACCOUNT BY ID
    @GetMapping("/{id}")
    public Account getAccountById(@PathVariable Long id)

    {
        return accountService.getAccountById(id).orElse(null);
    }


    // GET ALL TRANSACTIONS BY ACCOUNT ID
    @GetMapping("/{id}/transactions")
    public ResponseEntity<List<TransactionResponse>> getAccountTransactions(
            @PathVariable("id") Long id) {

        List<Transaction> list =
                transactionService.getTransactionsByAccountId(id);

        List<TransactionResponse> responseList = list.stream()
                .map(transaction ->
                        new TransactionResponse(
                                transaction.getId(),
                                transaction.getAmount(),
                                transaction.getType(),
                                transaction.getDateTime(),
                                transaction.getBalanceAferTransaction(),
                                transaction.getAccount().getId()
                        ))
                .toList();

        return ResponseEntity.ok(responseList);
    }

    //UPDATE ACCOUNT BY ID
    @PutMapping("/{id}")
    public ResponseEntity<Account> updateAccountById(@RequestBody Account updatedAccount,@PathVariable Long id)
    {
        Account updatedAccount1=accountService.updateAccount(updatedAccount, id);
        return ResponseEntity.ok(updatedAccount1);
    }
    
    //Delete the Account by Id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delteAccountById(@PathVariable Long id)
    {
    	
    	        accountService.deleteAccountById(id);
    	        return ResponseEntity.noContent().build();
    }

    





}
