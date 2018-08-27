package com.yaochow.user.service;

import com.yaochow.parent.common.ResultBase;
import com.yaochow.parent.dto.UserDTO;
import com.yaochow.user.service.impl.UserServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "service-data", fallback = UserServiceHystrix.class)
public interface UserService {

    @RequestMapping(value = "/user/insert", method = RequestMethod.POST)
    ResultBase<UserDTO> insert(@RequestBody UserDTO userDTO);

    @RequestMapping(value = "/user/getUserByAccountId/{accountId}", method = RequestMethod.GET)
    ResultBase<UserDTO> getUserByAccountId(@PathVariable("accountId") String accountId);

    @RequestMapping(value = "/user/update", method = RequestMethod.POST)
    ResultBase<UserDTO> update(@RequestBody UserDTO userDTO);

}
