package com.santander.banco811.controller;

import com.santander.banco811.dto.AccountRequest;
import com.santander.banco811.dto.AccountResponse;
import com.santander.banco811.model.Account;
import com.santander.banco811.model.AccountType;
import com.santander.banco811.projection.AccountView;
import com.santander.banco811.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {

    private static final String USERNAME = "USERNAME";

    @Autowired
    AccountService accountService;

    @GetMapping
    public List<AccountResponse> getAll() {
        return AccountResponse.toResponse(accountService.getAll());
    }

    @GetMapping("/view")
    public List<AccountView> getAllAccountViewByAccountType(@RequestParam AccountType accountType){
        return accountService.getAllViewByAccountType(accountType);
    }

    @GetMapping("/{id}")
    public AccountResponse getById(@PathVariable Integer id) {
        Account account = accountService.getById(id);
        return new AccountResponse(account);
    }

    @PostMapping
    public AccountResponse create(@RequestBody AccountRequest accountRequest) {
        var username = RequestContextHolder
                .getRequestAttributes()
                .getAttribute(USERNAME, RequestAttributes.SCOPE_REQUEST)
                .toString();

        return new AccountResponse(accountService.create(accountRequest, username));
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
