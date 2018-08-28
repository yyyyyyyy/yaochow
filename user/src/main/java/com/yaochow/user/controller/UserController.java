package com.yaochow.user.controller;

import com.alibaba.fastjson.JSONObject;
import com.yaochow.common.ErrorMsgEnum;
import com.yaochow.common.ResultBase;
import com.yaochow.dto.UserDTO;
import com.yaochow.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 获取、修改用户信息
 */
@Slf4j
@RestController
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private HttpServletRequest request;

    @RequestMapping(value = "/getUserByAccountId", method = RequestMethod.GET)
    public ResultBase<UserDTO> getUserByAccountId() {
        long start = System.currentTimeMillis();
        ResultBase<UserDTO> result = new ResultBase<>();
        log.info("get user by accountId begin");
        try {
            result = userService.getUserByAccountId((String) request.getSession().getAttribute("uidß"));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
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
            result = userService.update(userDTO);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            result.setSuccess(false);
            result.setErrorCode(ErrorMsgEnum.SYSTEM_ERROR.getErrorCode());
            result.setErrorMsg(ErrorMsgEnum.SYSTEM_ERROR.getErrorMsg());
        }
        log.info("update user by accountId, result : {}, cost : {}ms", result, System.currentTimeMillis() - start);
        return result;
    }
}
