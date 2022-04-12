package com.santander.banco811.model;

import com.santander.banco811.dto.TransactionRequest;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Table(schema = "class", name = "transaction")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "paying_account", referencedColumnName = "id")
    private Account payingAccount;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "receiving_account", referencedColumnName = "id")
    private Account receivingAccount;

    @Column(name = "date")
    @CreatedDate
    private LocalDateTime date;

    @Column(name = "amount")
    private BigDecimal amount;

    public Transaction(TransactionRequest transactionRequest){
        this.payingAccount = transactionRequest.getPayingAccount();
        this.receivingAccount = transactionRequest.getReceivingAccount();
        this.amount = transactionRequest.getAmount();
    }
}