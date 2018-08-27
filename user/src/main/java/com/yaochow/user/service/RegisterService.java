package com.yaochow.user.service;

import com.yaochow.parent.common.ResultBase;
import com.yaochow.parent.dto.AccountDTO;

public interface RegisterService {

    ResultBase register(AccountDTO accountDTO) throws Exception;

    ResultBase registerConfirm(String username, String email, String key) throws Exception;
}
