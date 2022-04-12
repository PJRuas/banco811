package com.santander.banco811.dto;

import com.santander.banco811.model.Transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactionResponse {
    Integer id;
    Integer payingAccountNumber;
    Integer receivingAccountNumber;
    BigDecimal amount;
    LocalDateTime date;

    public TransactionResponse(Transaction transaction){
        this.id = transaction.getId();
        this.payingAccountNumber = transaction.getPayingAccount().getAccountNumber();
        this.receivingAccountNumber = transaction.getReceivingAccount().getAccountNumber();
        this.amount = transaction.getAmount();
        this.date = transaction.getDate();
    }
}