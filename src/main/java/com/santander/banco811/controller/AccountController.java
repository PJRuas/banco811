package com.santander.banco811.controller;

import com.santander.banco811.dto.AccountRequest;
import com.santander.banco811.dto.AccountResponse;
import com.santander.banco811.model.Account;
import com.santander.banco811.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    AccountService accountService;

    @GetMapping
    public List<AccountResponse> getAll() {
        return AccountResponse.toResponse(accountService.getAll());
    }

    @PostMapping
    public AccountResponse create(@RequestParam Integer userId, @RequestBody AccountRequest accountRequest) {
        return accountService.create(accountRequest);
    }

    @GetMapping("/{id}")
    public AccountResponse getById(@PathVariable Integer id) {
        Account account = accountService.getById(id);
        return new AccountResponse(account);
    }

    @PutMapping("/{id}")
    public AccountResponse update(@PathVariable Integer id, @RequestBody AccountRequest accountRequest){
        Account account = accountService.update(accountRequest, id);
        return new AccountResponse(account);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        accountService.delete(id);
    }
}
