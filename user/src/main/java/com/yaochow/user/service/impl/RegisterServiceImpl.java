package com.yaochow.user.service.impl;

import com.yaochow.common.AccountStatusEnum;
import com.yaochow.common.ErrorMsgEnum;
import com.yaochow.common.ResultBase;
import com.yaochow.dto.AccountDTO;
import com.yaochow.dto.UserDTO;
import com.yaochow.user.service.AccountService;
import com.yaochow.user.service.RegisterService;
import com.yaochow.user.service.UserService;
import com.yaochow.user.util.MD5Util;
import com.yaochow.user.util.MailUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class RegisterServiceImpl implements RegisterService {

    private Logger log = LoggerFactory.getLogger(RegisterServiceImpl.class);

    @Autowired
    private AccountService accountService;
    @Autowired
    private UserService userService;
    @Value("${user.md5.password.key}")
    private String pswKey;
    @Value("${user.md5.uid.key}")
    private String uidKey;
    @Value("${user.email.content}")
    private String emailContent;

    @Override
    public ResultBase register(AccountDTO accountDTO) throws Exception {
        ResultBase<AccountDTO> resultBase = accountService.getAccountByEmail(accountDTO.getEmail());
        if (resultBase.isSuccess() && resultBase.getResult() != null) {
            resultBase.setSuccess(false);
            resultBase.setErrorMsg(ErrorMsgEnum.EMAIL_EXIST_ERROR.getErrorMsg());
            resultBase.setErrorCode(ErrorMsgEnum.EMAIL_EXIST_ERROR.getErrorCode());
            return resultBase;
        }

        resultBase = accountService.getAccountByUsername(accountDTO.getUsername());
        if (resultBase.isSuccess() && resultBase.getResult() != null) {
            resultBase.setSuccess(false);
            resultBase.setErrorMsg(ErrorMsgEnum.USERNAME_EXIST_ERROR.getErrorMsg());
            resultBase.setErrorCode(ErrorMsgEnum.USERNAME_EXIST_ERROR.getErrorCode());
            return resultBase;
        }

        accountDTO.setStatus(AccountStatusEnum.REGISTER.getCode());
        accountDTO.setPassword(MD5Util.md5(accountDTO.getPassword(), pswKey));
        resultBase = accountService.insert(accountDTO);
        if (resultBase.isSuccess()) {
            UserDTO userDTO = new UserDTO();
            userDTO.setAccountId(resultBase.getResult().getId());
            userService.insert(userDTO);

            String mailContent = String.format(emailContent,
                    resultBase.getResult().getUsername(),
                    resultBase.getResult().getEmail(),
                    MD5Util.md5(resultBase.getResult().getId(), uidKey));
            MailUtil.send(resultBase.getResult().getEmail(), mailContent);
        }
        return resultBase;
    }

    @Override
    public ResultBase registerConfirm(String username, String email, String key) throws Exception {
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setStatus(AccountStatusEnum.REGISTER.getCode());
        accountDTO.setEmail(email);
        accountDTO.setUsername(username);
        ResultBase<AccountDTO> resultBase = accountService.getAccount(accountDTO);

        if (!resultBase.isSuccess()) {
            return resultBase;
        }

        if (resultBase.getResult() == null) {
            resultBase.setSuccess(false);
            resultBase.setErrorMsg(ErrorMsgEnum.SYSTEM_ERROR.getErrorMsg());
            resultBase.setErrorCode(ErrorMsgEnum.SYSTEM_ERROR.getErrorCode());
            return resultBase;
        }
        if (Objects.equals(key, MD5Util.md5(resultBase.getResult().getId(), uidKey))) {
            resultBase.getResult().setStatus(AccountStatusEnum.CONFIRM.getCode());
            resultBase = accountService.update(resultBase.getResult());
        } else {
            resultBase.setSuccess(false);
            resultBase.setErrorMsg(ErrorMsgEnum.SYSTEM_ERROR.getErrorMsg());
            resultBase.setErrorCode(ErrorMsgEnum.SYSTEM_ERROR.getErrorCode());
        }
        return resultBase;
    }
}
