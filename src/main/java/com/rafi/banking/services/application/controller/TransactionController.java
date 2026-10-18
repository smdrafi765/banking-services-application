package com.rafi.banking.services.application.controller;

import com.rafi.banking.services.application.dto.TransactionRequest;
import com.rafi.banking.services.application.dto.TransactionResponse;
import com.rafi.banking.services.application.dto.TransferRequest;
import com.rafi.banking.services.application.entity.Transaction;
import com.rafi.banking.services.application.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    // Create and Save Transaction
    @PostMapping("/{accountId}")
    public ResponseEntity<TransactionResponse> createTransaction(@RequestBody TransactionRequest
                                                                         transactionRequest, @PathVariable("accountId") Long accountId) {


        Transaction savedTransaction = transactionService.createTransaction(transactionRequest, accountId);
       /*convert savedtransaction into transactionresponse to avoid Account details also for client in
        frontend output*/
        TransactionResponse transactionResponse = new TransactionResponse();
        transactionResponse.setId(savedTransaction.getId());
        transactionResponse.setAmount(savedTransaction.getAmount());
        transactionResponse.setType(savedTransaction.getType());
        transactionResponse.setDateTime(savedTransaction.getDateTime());
        transactionResponse.setAccountId(savedTransaction.getAccount().getId());


        return ResponseEntity.status(HttpStatus.CREATED).body(transactionResponse);


    }

    //TRANSFER MONEY FROM ONE ACCOUNT TO ANOTHER ACCOUNT
    @PostMapping("/transfer")
    public void Transfer(@RequestBody TransferRequest transferRequest) {

        transactionService.transferMoney(transferRequest);

    }


    // GET ALL TRANSACTIONS
    @GetMapping
    public ResponseEntity<List<TransactionResponse>> getAllTransactions() {

        List<Transaction> transactionList =
                transactionService.getAllTransactions();

        List<TransactionResponse> transactionResponseList =
                transactionList.stream()
                        .map(transaction -> {
                            TransactionResponse response = new TransactionResponse();

                            response.setId(transaction.getId());
                            response.setType(transaction.getType());
                            response.setAmount(transaction.getAmount());
                            response.setDateTime(transaction.getDateTime());

                            return response;
                        })
                        .toList();

        return ResponseEntity.ok(transactionResponseList);
    }

    //GET TRANSACTIONBY ID
    @GetMapping("/{id}")
    public ResponseEntity<TransactionResponse> getTransactionById(@PathVariable("id") Long id) {

        TransactionResponse transactionResponse = new TransactionResponse();
        Transaction existingTransaction = transactionService.getTransactionById(id);
        transactionResponse.setId(existingTransaction.getId());
        transactionResponse.setAmount(existingTransaction.getAmount());
        transactionResponse.setType(existingTransaction.getType());
        transactionResponse.setDateTime(existingTransaction.getDateTime());
        transactionResponse.setAccountId(existingTransaction.getAccount().getId());
        transactionResponse.setBalanceAferTransaction(existingTransaction.getBalanceAferTransaction());
        return ResponseEntity.ok(transactionResponse);

    }

    @GetMapping("/account/{id}")
    public ResponseEntity<List<TransactionResponse>> getTransactionsByAccountId(@PathVariable("id") Long accountId) {
        List<Transaction> list = transactionService.getTransactionsByAccountId(accountId);
        List<TransactionResponse> responseList =
                list.stream()
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
}
