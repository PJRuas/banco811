package com.santander.banco811.dto;

import com.santander.banco811.model.Account;
import com.santander.banco811.model.AccountType;
import com.santander.banco811.model.User;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class AccountResponse {
    private Integer id;
    private Integer accountNumber;
    private Integer agency;
    private AccountType accountType;
    private BigDecimal accountValue;
    private LocalDateTime creationDate;
    private LocalDateTime updateDate;
    private User user;

    public AccountResponse(Account account) {
        this.id = account.getId();
        this.accountNumber = account.getAccountNumber();
        this.agency = account.getAgency();
        this.accountType = account.getAccountType();
        this.accountValue = account.getAccountValue();
        this.creationDate = account.getCreationDate();
        this.updateDate = account.getUpdateDate();
        this.user = account.getUser();
    }

    public static List<AccountResponse> toResponse(List<Account> accounts){
        return accounts.stream().map(AccountResponse::new).collect(Collectors.toList());
    }
}
