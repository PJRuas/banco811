package com.santander.banco811.service.impl;

import com.santander.banco811.dto.TransactionRequest;
import com.santander.banco811.model.Transaction;
import com.santander.banco811.projection.TransactionView;
import com.santander.banco811.repository.AccountRepository;
import com.santander.banco811.repository.TransactionRepository;
import com.santander.banco811.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    AccountRepository accountRepository;

    private PageRequest generateDefaultPageable(Integer page, Integer size, int defaultPage, int defaultSize){
        page = page != null ? page : defaultPage;
        size = size != null ? size : defaultSize;
        return PageRequest.of(page, size, Sort.Direction.ASC);
    }

    @Override
    public Transaction getById(Integer id){
        return transactionRepository.findById(id).orElseThrow();
    }

    @Override
    public Page<Transaction> getAll(Integer page, Integer size) {
        PageRequest pageRequest = generateDefaultPageable(page, size, 0, 5);
        return transactionRepository.findAll(pageRequest);
    }

    @Override
    public List<Transaction> getByPayingAccount(Integer accountNumber, Integer page, Integer size) {
        PageRequest pageRequest = generateDefaultPageable(page, size, 0, 5);
        return transactionRepository.findByPayingAccount_accountNumber(accountNumber, pageRequest);
    }

    @Override
    public List<Transaction> getByReceivingAccount(Integer accountNumber, Integer page, Integer size) {
        PageRequest pageRequest = generateDefaultPageable(page, size, 0, 5);
        return transactionRepository.findByReceivingAccount_accountNumber(accountNumber, pageRequest);
    }

    @Override
    public List<Transaction> getByAmount(BigDecimal amount, Integer page, Integer size, String... condition) {
        PageRequest pageRequest = generateDefaultPageable(page, size, 0, 5);
        String result = condition != null ? condition[0] : "";

        Map<String,List<Transaction>> filters = new HashMap();
        filters.put(">", transactionRepository.findByAmountGreaterThanEqual(amount, pageRequest));
        filters.put("<", transactionRepository.findByAmountLessThanEqual(amount, pageRequest));

        return filters.getOrDefault(result, transactionRepository.findByAmount(amount, pageRequest));
    }

    @Override
    public Transaction create(TransactionRequest transactionRequest) {
        Transaction transactionToSave = new Transaction(transactionRequest);
        return transactionRepository.save(transactionToSave);
    }

    @Override
    public void delete(Integer id) {
        Transaction transactionToDelete = transactionRepository.findById(id).orElseThrow();
        transactionRepository.delete(transactionToDelete);
    }

    @Override
    public List<TransactionView> getAllTransactionView() {
        return transactionRepository.findByDateBefore(LocalDateTime.now());
    }
}