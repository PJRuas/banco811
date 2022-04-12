package com.santander.banco811.repository;

import com.santander.banco811.model.Transaction;
import com.santander.banco811.projection.TransactionView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    Page<Transaction> findAll(Pageable pageable);
    List<TransactionView> findByDateBefore(LocalDateTime localDateTime);
    List<Transaction> findByPayingAccount_accountNumber(Integer accountNumber, Pageable pageable);
    List<Transaction> findByReceivingAccount_accountNumber(Integer accountNumber, Pageable pageable);

//    List<Transaction> findByDateBefore(LocalDateTime date);
    List<Transaction> findByDateAfter(LocalDateTime date);
    List<Transaction> findByDate(LocalDateTime date);

    List<Transaction> findByAmount(BigDecimal amount, Pageable pageable);
    List<Transaction> findByAmountGreaterThanEqual(BigDecimal amount, Pageable pageable);
    List<Transaction> findByAmountLessThanEqual(BigDecimal amount, Pageable pageable);
    List<Transaction> findByAmountBetween(BigDecimal minAmount, BigDecimal maxAmount);
}
