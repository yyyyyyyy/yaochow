package com.yaochow.user.service;

import com.yaochow.parent.common.ResultBase;
import com.yaochow.parent.dto.AccountDTO;

public interface LoginService {

    ResultBase<AccountDTO> login(AccountDTO accountDTO) throws Exception;
}
