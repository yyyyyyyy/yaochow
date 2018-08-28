package com.yaochow.user.service;

import com.yaochow.common.ResultBase;
import com.yaochow.dto.AccountDTO;
import com.yaochow.user.service.impl.AccountServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "service-data", fallback = AccountServiceHystrix.class)
public interface AccountService {

    @RequestMapping(value = "/account/insert", method = RequestMethod.POST)
    ResultBase<AccountDTO> insert(@RequestBody AccountDTO accountDTO);

    @RequestMapping(value = "/account/getAccountByUsername/{username}", method = RequestMethod.GET)
    ResultBase<AccountDTO> getAccountByUsername(@PathVariable("username") String username);

    @RequestMapping(value = "/account/update", method = RequestMethod.POST)
    ResultBase<AccountDTO> update(@RequestBody AccountDTO accountDTO);

    @RequestMapping(value = "/account/getAccountByEmail/{email}", method = RequestMethod.GET)
    ResultBase<AccountDTO> getAccountByEmail(@PathVariable("email") String email);

    @RequestMapping(value = "/account/getAccount", method = RequestMethod.POST)
    ResultBase<AccountDTO> getAccount(@RequestBody AccountDTO accountDTO);
}
