package com.santander.banco811.repository;

import com.santander.banco811.model.Account;
import com.santander.banco811.model.AccountType;
import com.santander.banco811.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;

@DataJpaTest
@Profile("test")
public class AccountRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    AccountRepository accountRepository;

    private Account generateTestAccount(AccountType accountType) {
        Account testAccount = new Account();
        testAccount.setAccountType(accountType);
        return testAccount;
    }

    @Test
    public void validate_findAll_empty_if_repository_empty() {
        var accounts = accountRepository.findAll();

        Assertions.assertEquals(Arrays.asList(), accounts);
    }

    @Test
    public void return_all_account_filter_by_owner_cpf_if_exists_on_database(){
        Account account = new Account();
        Account account1 = new Account();
        Account account2 = new Account();

        User user = new User();
        User user1 = new User();

        user.setCpf("01234567890");
        user1.setCpf("09876543210");

        account.setUser(user);
        account1.setUser(user);
        account2.setUser(user1);

        entityManager.persist(account);
        entityManager.persist(account1);
        entityManager.persist(account2);

        var accounts = accountRepository.findByUser_cpf(user.getCpf());

        Assertions.assertEquals(Arrays.asList(account1, account), accounts);
    }
}