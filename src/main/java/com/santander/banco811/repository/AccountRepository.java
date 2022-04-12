package com.santander.banco811.repository;


import com.santander.banco811.model.Account;
import com.santander.banco811.model.AccountType;
import com.santander.banco811.projection.AccountView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    List<Account> findByAccountValueLessThan(BigDecimal value);
    List<Account> findByAccountValueLessThanEqual(BigDecimal value);
    List<Account> findByAccountValueGreaterThan(BigDecimal value);
    List<Account> findByAccountValueGreaterThanEqual(BigDecimal value);

    List<Account> findByAccountValueBetween(BigDecimal initialValue, BigDecimal finalValue);
    List<Account> findByAccountValueIn(List<BigDecimal> values);

    List<Account> findByAccountTypeAndAccountValueBetweenOrderByAccountValue(AccountType accountType, BigDecimal initialValue, BigDecimal finalValue);

    List<Account> findByUser_cpf(String cpf);

    Boolean existsByAccountType(AccountType accountType);

    @Query("select a from Account a " +
            "where (a.accountType = :accountType AND a.user.cpf = :cpf) " +
            "OR (a.accountType = :accountType OR a.accountValue = :accountValue)")
    List<Account> findByAccountTypeAndCpfOrAccountTypeAndAccountValue(
            @Param("accountType") AccountType accountType,
            @Param("cpf") String cpf,
            @Param("accountValue") BigDecimal accountValue
    );

//    @Query("select a from Account a " +
//            "where (a.account_type = :accountType AND " +
//            "a.creation_date >= :creationDate) " +
//            "OR a.account_value = :accountValue")
//    List<Account> findByCreationDateAndAccountTypeOrAccountValue(
//            @Param("creationDate") LocalDateTime creationDate,
//            @Param("accountType") AccountType accountType,
//            @Param("accountValue") BigDecimal accountValue
//            );

    @Query("select a from Account a " + "where a.accountType = :accountType and a.user.name = :name")
    List<Account>findByAccountTypeAndUserName(@Param("accountType") AccountType accountType,
                                              @Param("name") String name);

    List<AccountView> findAllByAccountType(AccountType accountType);
}