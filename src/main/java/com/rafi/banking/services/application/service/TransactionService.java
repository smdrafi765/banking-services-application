package com.rafi.banking.services.application.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.rafi.banking.services.application.dto.TransactionRequest;
import com.rafi.banking.services.application.dto.TransferRequest;
import com.rafi.banking.services.application.entity.Account;
import com.rafi.banking.services.application.repository.AccountRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.rafi.banking.services.application.entity.Transaction;
import com.rafi.banking.services.application.repository.TransactionRepository;
import org.springframework.web.server.ResponseStatusException;
import transaction_enum.TransactionType;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;

    public TransactionService(TransactionRepository transactionRepository,
                              AccountRepository accountRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
    }

    // Create and save Transaction
    public Transaction createTransaction(TransactionRequest transactionRequest, Long id) {


        Account  existingAccount= accountRepository.findById(id).
                orElseThrow(()->new RuntimeException("Account not found with id: "+id));
        if(transactionRequest.getAmount()==null ||transactionRequest.getAmount().compareTo(BigDecimal.ZERO)<=0)
            {
            throw new RuntimeException("Amount is lower than Zero");

        } else if (transactionRequest.getType()==TransactionType.DEPOSIT) {

            existingAccount.setBalance(existingAccount.getBalance().add(transactionRequest.getAmount()));
        }

        else if (transactionRequest.getType()==TransactionType.WITHDRAWAL) {

            if(transactionRequest.getAmount().compareTo(existingAccount.getBalance())>0)
                throw new RuntimeException("Account has Insufficient Funds");
            existingAccount.setBalance(existingAccount.getBalance().subtract(transactionRequest.getAmount()));

        }
        // convert transaction request into transaction
        Transaction newTransaction = new Transaction();
        newTransaction.setAmount(transactionRequest.getAmount());
        newTransaction.setType(transactionRequest.getType());
        newTransaction.setAccount(existingAccount);
        newTransaction.setBalanceAferTransaction(existingAccount.getBalance());
        newTransaction.setDateTime(LocalDateTime.now()); // set the time while saving in database
        accountRepository.save(existingAccount);
        return transactionRepository.save(newTransaction);


    }

    
    // get Transaction by id
    public Transaction getTransactionById(long id)
    {
    	
    	
    	return transactionRepository.findById(id).
    			orElseThrow(()->new RuntimeException("Transaction not found with id "+id));	
    	
    }

    //Get all transactions
    public List<Transaction> getAllTransactions()
    {

        return transactionRepository.findAll();

    }

    //get Transaction by Id
    public Transaction getTransactionById(Long id)
    {

        return transactionRepository.findById(id).orElseThrow(()->
                new RuntimeException("Transaction not found with id "+id));
    }

    //get all Account Transactions
    public List<Transaction>  getTransactionsByAccountId(Long id)
    {
        Account existAccount= accountRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Account Not Found"));
        return existAccount.getTransactions();
    }

    // Transfer Money from one account to another
    public void  transferMoney(TransferRequest request)
    {

        Long fromAccountId = request.getFromAccountId();
        Long toAccountId = request.getToAccountId();
        BigDecimal amount = request.getAmount();

        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Amount must be greater than zero"
            );
        }
        if(fromAccountId.equals(toAccountId))
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST,"cant Transfer Money to same accounts");

        Account fromAccount = accountRepository.findById(fromAccountId)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND, "Source Account Not Found"));

        Account toAccount = accountRepository.findById(toAccountId)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Destination Account Not Found"
                        ));
        if(amount.compareTo(fromAccount.getBalance())>0)
        {
            throw new RuntimeException("Insufficient Funds");
        }
       fromAccount.setBalance(fromAccount.getBalance().subtract(amount)); //subtract amount
        toAccount.setBalance(toAccount.getBalance().add(amount));
        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);
        Transaction transaction1= new Transaction();
        transaction1.setAmount(amount);
        transaction1.setType(TransactionType.WITHDRAWAL);
        transaction1.setAccount(fromAccount);
        transaction1.setBalanceAferTransaction(fromAccount.getBalance());
        transaction1.setDateTime(LocalDateTime.now());
        transactionRepository.save(transaction1);
        Transaction transaction2= new Transaction();
        transaction2.setAmount(amount);
        transaction2.setType(TransactionType.DEPOSIT);
        transaction2.setAccount(toAccount);
        transaction2.setBalanceAferTransaction(toAccount.getBalance());
        transaction2.setDateTime(LocalDateTime.now());
        transactionRepository.save(transaction2);



    }
    
    
    
    
    
    
    
    
    
    
    


}
