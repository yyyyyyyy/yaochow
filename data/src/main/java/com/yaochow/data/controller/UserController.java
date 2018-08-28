package com.yaochow.data.controller;

import com.alibaba.fastjson.JSONObject;
import com.yaochow.data.service.UserService;
import com.yaochow.common.ErrorMsgEnum;
import com.yaochow.common.ResultBase;
import com.yaochow.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userServiceImpl;

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public ResultBase<UserDTO> insert(@RequestBody UserDTO userDTO) {
        long start = System.currentTimeMillis();
        ResultBase<UserDTO> result = new ResultBase<>();
        log.info("insert, param : {}", JSONObject.toJSONString(userDTO));
        try {
            UserDTO userDTORes = userServiceImpl.insert(userDTO);
            result.setSuccess(true);
            result.setResult(userDTORes);
        } catch (Exception e) {
            log.info(e.getMessage(), e);
            result.setSuccess(false);
            result.setErrorCode(ErrorMsgEnum.SYSTEM_ERROR.getErrorCode());
            result.setErrorMsg(ErrorMsgEnum.SYSTEM_ERROR.getErrorMsg());
        }
        log.info("insert, result : {}, cost : {}ms", JSONObject.toJSONString(result), System.currentTimeMillis() - start);
        return result;
    }

    @RequestMapping(value = "/getUserByAccountId/{accountId}", method = RequestMethod.GET)
    public ResultBase<UserDTO> getUserByAccountId(@PathVariable String accountId) {
        long start = System.currentTimeMillis();
        ResultBase<UserDTO> result = new ResultBase<>();
        try {
            log.info("get user by accountId : {}", accountId);
            UserDTO userDTORes = userServiceImpl.getUserByAccountId(accountId);
            result.setSuccess(true);
            result.setResult(userDTORes);
        } catch (Exception e) {
            log.info(e.getMessage(), e);
            result.setSuccess(false);
            result.setErrorCode(ErrorMsgEnum.SYSTEM_ERROR.getErrorCode());
            result.setErrorMsg(ErrorMsgEnum.SYSTEM_ERROR.getErrorMsg());
        }
        log.info("get user by accountId, result : {}, cost : {}ms", JSONObject.toJSONString(result), System.currentTimeMillis() - start);
        return result;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResultBase<UserDTO> update(@RequestBody UserDTO userDTO) {
        long start = System.currentTimeMillis();
        ResultBase<UserDTO> result = new ResultBase<>();
        log.info("update user by accountId, param : {}", JSONObject.toJSONString(userDTO));
        try {
            UserDTO userDTORes = userServiceImpl.update(userDTO);
            result.setSuccess(true);
            result.setResult(userDTORes);
        } catch (Exception e) {
            log.info(e.getMessage(), e);
            result.setSuccess(false);
            result.setErrorCode(ErrorMsgEnum.SYSTEM_ERROR.getErrorCode());
            result.setErrorMsg(ErrorMsgEnum.SYSTEM_ERROR.getErrorMsg());
        }
        log.info("update user by accountId, result : {}, cost : {}ms", JSONObject.toJSONString(result), System.currentTimeMillis() - start);
        return result;
    }
}
