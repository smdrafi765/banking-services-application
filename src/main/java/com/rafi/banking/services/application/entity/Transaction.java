package com.rafi.banking.services.application.entity;

import jakarta.persistence.*;
import transaction_enum.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "transactions")
public class Transaction {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private BigDecimal amount;
    
    @Enumerated(EnumType.STRING)
    private TransactionType type;
    private LocalDateTime dateTime;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

	private BigDecimal balanceAferTransaction;

	public BigDecimal getBalanceAferTransaction() {
		return balanceAferTransaction;
	}

	public void setBalanceAferTransaction(BigDecimal balanceAferTransaction) {
		this.balanceAferTransaction = balanceAferTransaction;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}


   
}
