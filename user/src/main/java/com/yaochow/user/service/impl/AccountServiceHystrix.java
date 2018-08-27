package com.yaochow.user.service.impl;

import com.yaochow.parent.common.HystrixEffectiveEnum;
import com.yaochow.parent.common.ResultBase;
import com.yaochow.parent.dto.AccountDTO;
import com.yaochow.user.service.AccountService;
import org.springframework.stereotype.Component;

@Component
public class AccountServiceHystrix implements AccountService {

    @Override
    public ResultBase<AccountDTO> insert(AccountDTO accountDTO) {
        return HystrixEffectiveEnum.NORMAL_EFFECTIVE.getResultBase();
    }

    @Override
    public ResultBase<AccountDTO> getAccountByUsername(String username) {
        return HystrixEffectiveEnum.NORMAL_EFFECTIVE.getResultBase();
    }

    @Override
    public ResultBase<AccountDTO> update(AccountDTO accountDTO) {
        return HystrixEffectiveEnum.NORMAL_EFFECTIVE.getResultBase();
    }

    @Override
    public ResultBase<AccountDTO> getAccountByEmail(String email) {
        return HystrixEffectiveEnum.NORMAL_EFFECTIVE.getResultBase();
    }

    @Override
    public ResultBase<AccountDTO> getAccount(AccountDTO accountDTO) {
        return HystrixEffectiveEnum.NORMAL_EFFECTIVE.getResultBase();
    }
}
