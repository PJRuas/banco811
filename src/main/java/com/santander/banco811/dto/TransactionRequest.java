package com.santander.banco811.dto;

import com.santander.banco811.model.Account;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class TransactionRequest {
    private Account payingAccount;
    private Account receivingAccount;
    private BigDecimal amount;
}