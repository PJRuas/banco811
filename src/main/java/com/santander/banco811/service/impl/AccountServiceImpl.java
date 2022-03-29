package com.santander.banco811.service.impl;

import com.santander.banco811.dto.AccountRequest;
import com.santander.banco811.dto.AccountResponse;
import com.santander.banco811.model.Account;
import com.santander.banco811.repository.AccountRepository;
import com.santander.banco811.service.AccountService;
import com.santander.banco811.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountRepository accountRepository;
    @Autowired
    UserService userService;

    @Override
    public List<Account> getAll() {
        return accountRepository.findAll();
    }

    @Override
    public AccountResponse create(AccountRequest accountRequest) {
        Account accountToSave = new Account(accountRequest);
        accountToSave.setUser(userService.getById(accountRequest.getUserId()));
        accountRepository.save(accountToSave);
        return new AccountResponse(accountToSave);
    }

    @Override
    public Account getById(Integer id) {
        return accountRepository.findById(id).orElseThrow();
    }

    @Override
    public Account update(AccountRequest accountRequest, Integer id) {
       Account accountToUpdate = getById(id);
       accountToUpdate.setAccountNumber(accountRequest.getAccountNumber());
       accountToUpdate.setAgency(accountRequest.getAgency());
       accountToUpdate.setAccountType(accountRequest.getAccountType());
       accountToUpdate.setAccountValue(accountRequest.getAccountValue());
       accountToUpdate.setUser(userService.getById(accountRequest.getUserId()));
       return accountRepository.save(accountToUpdate);
    }

    @Override
    public void delete(Integer id) {
        Account accountToDelete = getById(id);
        accountRepository.delete(accountToDelete);
    }
}
