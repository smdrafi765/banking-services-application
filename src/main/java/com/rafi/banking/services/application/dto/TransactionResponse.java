package com.rafi.banking.services.application.dto;

import com.rafi.banking.services.application.entity.Account;
import transaction_enum.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactionResponse {

    private long id;
    private BigDecimal amount;
    private TransactionType type;
    private LocalDateTime dateTime;
    private BigDecimal balanceAferTransaction;
    private Long accountId;

    public TransactionResponse() {
    }

    public TransactionResponse(long id, BigDecimal amount, TransactionType type, LocalDateTime dateTime, BigDecimal balanceAferTransaction, Long accountId) {
        this.id = id;
        this.amount = amount;
        this.type = type;
        this.dateTime = dateTime;
        this.balanceAferTransaction = balanceAferTransaction;
        this.accountId = accountId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public BigDecimal getBalanceAferTransaction() {
        return balanceAferTransaction;
    }

    public void setBalanceAferTransaction(BigDecimal balanceAferTransaction) {
        this.balanceAferTransaction = balanceAferTransaction;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }
}
