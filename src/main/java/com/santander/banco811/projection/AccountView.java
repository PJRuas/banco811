package com.santander.banco811.projection;

import com.santander.banco811.model.AccountType;
import org.springframework.beans.factory.annotation.Value;

public interface AccountView {
    Integer getAccountValue();
    AccountType getAccountType();
    UserView getUser();

    @Value("#{target.accountNumber + '-' + target.agency}")
    String getAgency();
}
