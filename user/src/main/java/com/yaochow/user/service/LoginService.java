package com.yaochow.user.service;

import com.yaochow.common.ResultBase;
import com.yaochow.dto.AccountDTO;

public interface LoginService {

    ResultBase<AccountDTO> login(AccountDTO accountDTO) throws Exception;
}
