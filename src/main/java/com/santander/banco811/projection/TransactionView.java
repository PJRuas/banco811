package com.santander.banco811.projection;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface TransactionView {
    AccountView getPayingAccount();
    AccountView getReceivingAccount();
    LocalDateTime getDate();
    BigDecimal getAmount();
}
