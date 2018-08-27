package com.yaochow.data.service;

import com.yaochow.parent.dto.AccountDTO;

public interface AccountService {

    AccountDTO insert(AccountDTO accountDTO);

    AccountDTO getAccountByUsername(String username);

    AccountDTO updateAccountById(AccountDTO accountDTO);

    AccountDTO getAccountByEmail(String email);

    AccountDTO getAccount(AccountDTO accountDTO);
}
