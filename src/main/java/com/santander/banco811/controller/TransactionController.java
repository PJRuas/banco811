package com.santander.banco811.controller;

import com.santander.banco811.dto.TransactionRequest;
import com.santander.banco811.dto.TransactionResponse;
import com.santander.banco811.model.Transaction;
import com.santander.banco811.projection.TransactionView;
import com.santander.banco811.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @GetMapping
    public List<TransactionResponse> getAll(@RequestParam(required = false) Integer page,
                                            @RequestParam(required = false) Integer size,
                                            @RequestParam(required = false) BigDecimal amount,
                                            @RequestParam(required = false) String condition) {
        if (amount != null) {
            List<Transaction> result = transactionService.getByAmount(amount, page, size, condition);
            return result.stream().map(TransactionResponse :: new).collect(Collectors.toList());
        } else {
            Page<Transaction> result = transactionService.getAll(page, size);
            return result.stream().map(TransactionResponse :: new).collect(Collectors.toList());
        }
    }

    @GetMapping("/{id}")
    public TransactionResponse getById(@PathVariable Integer id){
        Transaction transaction = transactionService.getById(id);
        return new TransactionResponse(transaction);
    }

    @GetMapping("/{accountNumber}")
    public List<TransactionResponse> getByAccount(@PathVariable Integer accountNumber,
                                                  @RequestParam(required = false) String role,
                                                  @RequestParam(required = false) Integer page,
                                                  @RequestParam(required = false) Integer size){
        List<Transaction> result;
        if (role.toLowerCase().contains("pay")){
            result = transactionService.getByPayingAccount(accountNumber, page, size);
        } else if (role.toLowerCase().contains("receiv")) {
            result = transactionService.getByReceivingAccount(accountNumber, page, size);
        } else {
            result = transactionService.getByReceivingAccount(accountNumber, page, size);
            result.addAll(transactionService.getByPayingAccount(accountNumber, page, size));
        }
        return result.stream().map(TransactionResponse :: new).collect(Collectors.toList());
    }

    @GetMapping("/view")
    public List<TransactionView> getAllTransactionView(){
        return transactionService.getAllTransactionView();
    }

    @PostMapping
    public TransactionResponse create(@RequestBody TransactionRequest transactionRequest){
        Transaction transaction = transactionService.create(transactionRequest);
        return new TransactionResponse(transaction);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {transactionService.delete(id);}

}
