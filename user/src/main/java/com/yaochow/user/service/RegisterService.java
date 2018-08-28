package com.yaochow.user.service;

import com.yaochow.common.ResultBase;
import com.yaochow.dto.AccountDTO;

public interface RegisterService {

    ResultBase register(AccountDTO accountDTO) throws Exception;

    ResultBase registerConfirm(String username, String email, String key) throws Exception;
}
