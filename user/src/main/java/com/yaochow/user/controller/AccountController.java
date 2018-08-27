package com.yaochow.user.controller;

import com.alibaba.fastjson.JSONObject;
import com.yaochow.parent.common.ErrorMsgEnum;
import com.yaochow.parent.common.ResultBase;
import com.yaochow.parent.dto.AccountDTO;
import com.yaochow.user.service.AccountService;
import com.yaochow.user.service.LoginService;
import com.yaochow.user.service.RegisterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


/**
 * 登陆、注册、修改登陆密码
 */
@Slf4j
@RestController
public class AccountController {

    @Autowired
    private AccountService accountService;
    @Autowired
    private RegisterService registerServiceImpl;
    @Autowired
    private LoginService loginServiceImpl;
    @Autowired
    private HttpServletRequest request;
    @Value("${user.register.confirm.result}")
    private String confirmResult;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResultBase login(@RequestBody AccountDTO accountDTO) {

        long start = System.currentTimeMillis();
        ResultBase<AccountDTO> result = new ResultBase<>();
        log.info("login, param : {}", JSONObject.toJSONString(accountDTO));
        try {
            result = loginServiceImpl.login(accountDTO);
            if (result.isSuccess()) {
                request.getSession().setAttribute("uid", result.getResult().getId());
            }
            result.setResult(null);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            result.setSuccess(false);
            result.setErrorCode(ErrorMsgEnum.SYSTEM_ERROR.getErrorCode());
            result.setErrorMsg(ErrorMsgEnum.SYSTEM_ERROR.getErrorMsg());
        }
        log.info("login, result : {}, cost : {}ms", JSONObject.toJSONString(result), System.currentTimeMillis() - start);
        return result;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResultBase register(@RequestBody AccountDTO accountDTO) {
        long start = System.currentTimeMillis();
        ResultBase result = new ResultBase();
        log.info("register, param : {}", JSONObject.toJSONString(accountDTO));
        try {
            result = registerServiceImpl.register(accountDTO);
            result.setResult(null);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            result.setSuccess(false);
            result.setErrorCode(ErrorMsgEnum.SYSTEM_ERROR.getErrorCode());
            result.setErrorMsg(ErrorMsgEnum.SYSTEM_ERROR.getErrorMsg());
        }
        log.info("register, result : {}, cost : {}ms", JSONObject.toJSONString(result), System.currentTimeMillis() - start);
        return result;
    }

    @RequestMapping(value = "/confirm", method = RequestMethod.GET)
    public String registerConfirm(@RequestParam String username, @RequestParam String email, @RequestParam String key) {
        long start = System.currentTimeMillis();
        String result;
        log.info("register confirm email : {}, key : {}", email, key);
        try {
            ResultBase resultBase = registerServiceImpl.registerConfirm(username, email, key);
            if (resultBase.isSuccess()) {
                result = confirmResult;
            } else {
                result = String.format("<script>alert('%s');</script>", resultBase.getErrorMsg());
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            result = "<script>alert('System Error');</script>";
        }
        log.info("register confirm, result : {}, cost : {}ms", result, System.currentTimeMillis() - start);
        return result;
    }

    @RequestMapping(value = "/updateAccountById", method = RequestMethod.POST)
    public ResultBase updateAccountById(@RequestBody AccountDTO accountDTO) {
        long start = System.currentTimeMillis();
        ResultBase result = new ResultBase();
        log.info("update account by id, param : {}", JSONObject.toJSONString(accountDTO));
        try {
            accountDTO.setId((String) request.getSession().getAttribute("uidß"));
            result = accountService.update(accountDTO);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        log.info("update account by id, result : {}, cost : {}ms", JSONObject.toJSONString(result), System.currentTimeMillis() - start);
        return result;
    }

    @RequestMapping(value = "/getAccountByUsername/{username}", method = RequestMethod.GET)
    public ResultBase<AccountDTO> getAccountByUsername(@PathVariable String username) {
        long start = System.currentTimeMillis();
        ResultBase<AccountDTO> result = new ResultBase<>();
        log.info("get account by username : {}", username);
        try {
            result = accountService.getAccountByUsername(username);
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
            result = accountService.getAccountByEmail(email);
        } catch (Exception e) {
            log.info(e.getMessage(), e);
            result.setSuccess(false);
            result.setErrorCode(ErrorMsgEnum.SYSTEM_ERROR.getErrorCode());
            result.setErrorMsg(ErrorMsgEnum.SYSTEM_ERROR.getErrorMsg());
        }
        log.info("get account by email, result : {}, cost : {}ms", JSONObject.toJSONString(result), System.currentTimeMillis() - start);
        return result;
    }
}
