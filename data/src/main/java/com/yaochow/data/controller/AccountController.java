package com.yaochow.data.controller;

import com.alibaba.fastjson.JSONObject;
import com.yaochow.data.service.AccountService;

import com.yaochow.common.ErrorMsgEnum;
import com.yaochow.common.ResultBase;
import com.yaochow.dto.AccountDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
@Slf4j
public class AccountController {

    @Autowired
    private AccountService accountServiceImpl;

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public ResultBase<AccountDTO> insert(@RequestBody AccountDTO accountDTO) {
        long start = System.currentTimeMillis();
        ResultBase<AccountDTO> result = new ResultBase<>();
        log.info("insert, param : {}", JSONObject.toJSONString(accountDTO));
        try {
            AccountDTO accountRes = accountServiceImpl.insert(accountDTO);
            result.setSuccess(true);
            result.setResult(accountRes);
        } catch (Exception e) {
            log.info(e.getMessage(), e);
            result.setSuccess(false);
            result.setErrorCode(ErrorMsgEnum.SYSTEM_ERROR.getErrorCode());
            result.setErrorMsg(ErrorMsgEnum.SYSTEM_ERROR.getErrorMsg());
        }
        log.info("insert, result : {}, cost : {}ms", JSONObject.toJSONString(result), System.currentTimeMillis() - start);
        return result;
    }

    @RequestMapping(value = "/getAccountByUsername/{username}", method = RequestMethod.GET)
    public ResultBase<AccountDTO> getAccountByUsername(@PathVariable String username) {
        long start = System.currentTimeMillis();
        ResultBase<AccountDTO> result = new ResultBase<>();
        log.info("get account by username : {}", username);
        try {
            AccountDTO accountDTORes = accountServiceImpl.getAccountByUsername(username);
            result.setSuccess(true);
            result.setResult(accountDTORes);
        } catch (Exception e) {
            log.info(e.getMessage(), e);
            result.setSuccess(false);
            result.setErrorCode(ErrorMsgEnum.SYSTEM_ERROR.getErrorCode());
            result.setErrorMsg(ErrorMsgEnum.SYSTEM_ERROR.getErrorMsg());
        }
        log.info("get account by username, result : {}, cost : {}ms", JSONObject.toJSONString(result), System.currentTimeMillis() - start);
        return result;
    }

    @RequestMapping(value = "/getAccountByEmail/{email}", method = RequestMethod.GET)
    public ResultBase<AccountDTO> getAccountByEmail(@PathVariable String email) {
        long start = System.currentTimeMillis();
        ResultBase<AccountDTO> result = new ResultBase<>();
        log.info("get account by email : {}", email);
        try {
            AccountDTO accountDTORes = accountServiceImpl.getAccountByEmail(email);
            result.setSuccess(true);
            result.setResult(accountDTORes);
        } catch (Exception e) {
            log.info(e.getMessage(), e);
            result.setSuccess(false);
            result.setErrorCode(ErrorMsgEnum.SYSTEM_ERROR.getErrorCode());
            result.setErrorMsg(ErrorMsgEnum.SYSTEM_ERROR.getErrorMsg());
        }
        log.info("get account by email, result : {}, cost : {}ms", JSONObject.toJSONString(result), System.currentTimeMillis() - start);
        return result;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResultBase<AccountDTO> update(@RequestBody AccountDTO accountDTO) {
        long start = System.currentTimeMillis();
        ResultBase<AccountDTO> result = new ResultBase<>();
        log.info("update , param : {}", JSONObject.toJSONString(accountDTO));
        try {
            if (accountDTO.getId() == null) {
                result.setSuccess(false);
                result.setErrorCode(ErrorMsgEnum.ACCOUNT_ID_NOT_EXIST_ERROR.getErrorCode());
                result.setErrorMsg(ErrorMsgEnum.ACCOUNT_ID_NOT_EXIST_ERROR.getErrorMsg());
                return result;
            }
            AccountDTO accountDTORes = accountServiceImpl.updateAccountById(accountDTO);
            result.setSuccess(true);
            result.setResult(accountDTORes);
        } catch (Exception e) {
            log.info(e.getMessage(), e);
            result.setSuccess(false);
            result.setErrorCode(ErrorMsgEnum.SYSTEM_ERROR.getErrorCode());
            result.setErrorMsg(ErrorMsgEnum.SYSTEM_ERROR.getErrorMsg());
        }
        log.info("update , result : {}, cost : {}ms", result, System.currentTimeMillis() - start);
        return result;
    }

    @RequestMapping(value = "/getAccount", method = RequestMethod.POST)
    public ResultBase<AccountDTO> getAccount(@RequestBody AccountDTO accountDTO) {
        long start = System.currentTimeMillis();
        ResultBase<AccountDTO> result = new ResultBase<>();
        log.info("get account , param : {}", JSONObject.toJSONString(accountDTO));
        try {
            AccountDTO accountDTORes = accountServiceImpl.getAccount(accountDTO);
            result.setSuccess(true);
            result.setResult(accountDTORes);
        } catch (Exception e) {
            log.info(e.getMessage(), e);
            result.setSuccess(false);
            result.setErrorCode(ErrorMsgEnum.SYSTEM_ERROR.getErrorCode());
            result.setErrorMsg(ErrorMsgEnum.SYSTEM_ERROR.getErrorMsg());
        }
        log.info("get account , result : {}, cost : {}ms", result, System.currentTimeMillis() - start);
        return result;
    }

}
