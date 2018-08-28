package com.yaochow.user.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.yaochow.common.ResultBase;
import com.yaochow.dto.AccountDTO;
import com.yaochow.user.service.AccountService;
import com.yaochow.user.service.PasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PasswordServiceImpl implements PasswordService {
    @Autowired
    private AccountService accountService;

    @Override
    public String changePassword(String accountJson) {
        AccountDTO accountDTO = JSONObject.parseObject(accountJson, AccountDTO.class);
        ResultBase<AccountDTO> resultBase = accountService.update(accountDTO);
        return "";
    }
}
