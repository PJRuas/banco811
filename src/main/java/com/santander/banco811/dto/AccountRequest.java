package com.santander.banco811.dto;

import com.santander.banco811.model.AccountType;
import com.santander.banco811.model.User;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class AccountRequest {
    private Integer accountNumber;
    private Integer agency;
    private AccountType accountType;
    private BigDecimal accountValue;
    private Integer userId;
}
