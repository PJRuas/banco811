package com.santander.banco811.service;

import com.santander.banco811.dto.TransactionRequest;
import com.santander.banco811.model.Transaction;
import com.santander.banco811.projection.TransactionView;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public interface TransactionService {
    Page<Transaction> getAll(Integer page, Integer size);
    List<Transaction> getByPayingAccount(Integer accountNumber, Integer page, Integer size);
    List<Transaction> getByReceivingAccount(Integer accountNumber, Integer page, Integer size);
    List<Transaction> getByAmount(BigDecimal amount, Integer page, Integer size, String... condition);
    Transaction getById(Integer id);
    Transaction create(TransactionRequest transactionRequest);
    void delete(Integer id);
    List<TransactionView> getAllTransactionView();
}
