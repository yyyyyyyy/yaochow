package com.yaochow.user.service.impl;

import com.yaochow.common.AccountStatusEnum;
import com.yaochow.common.ErrorMsgEnum;
import com.yaochow.common.ResultBase;
import com.yaochow.dto.AccountDTO;
import com.yaochow.user.service.AccountService;
import com.yaochow.user.service.LoginService;
import com.yaochow.user.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AccountService accountService;
    @Value("${user.md5.password.key}")
    private String pswKey;

    @Override
    public ResultBase<AccountDTO> login(AccountDTO accountDTO) throws Exception {
        ResultBase<AccountDTO> resultBase = accountService.getAccountByUsername(accountDTO.getUsername());

        if (!resultBase.isSuccess()) {
            return resultBase;
        }

        if (resultBase.getResult() == null) {
            resultBase.setSuccess(false);
            resultBase.setErrorMsg(ErrorMsgEnum.ACCOUNT_NOT_EXIST_ERROR.getErrorMsg());
            resultBase.setErrorCode(ErrorMsgEnum.ACCOUNT_NOT_EXIST_ERROR.getErrorCode());
            return resultBase;
        }

        if (!Objects.equals(AccountStatusEnum.CONFIRM.getCode(), resultBase.getResult().getStatus())) {
            resultBase.setSuccess(false);
            resultBase.setErrorMsg(ErrorMsgEnum.CONFIRM_ERROR.getErrorMsg());
            resultBase.setErrorCode(ErrorMsgEnum.CONFIRM_ERROR.getErrorCode());
            return resultBase;
        }

        if (!MD5Util.verify(accountDTO.getPassword(), pswKey, resultBase.getResult().getPassword())) {
            resultBase.setSuccess(false);
            resultBase.setErrorMsg(ErrorMsgEnum.PASSWORD_INCORRECT_ERROR.getErrorMsg());
            resultBase.setErrorCode(ErrorMsgEnum.PASSWORD_INCORRECT_ERROR.getErrorCode());
        }
        return resultBase;
    }
}
