package com.santander.banco811.service;

import com.santander.banco811.dto.AccountRequest;
import com.santander.banco811.dto.AccountResponse;
import com.santander.banco811.model.Account;
import com.santander.banco811.model.AccountType;
import com.santander.banco811.projection.AccountView;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AccountService {
    List<Account> getAll();

    List<AccountView> getAllViewByAccountType(AccountType accountType);

    Account create(AccountRequest accountRequest, String username);

    Account getById(Integer id);

    Account update(AccountRequest accountRequest, Integer id);

    void delete(Integer id);
}