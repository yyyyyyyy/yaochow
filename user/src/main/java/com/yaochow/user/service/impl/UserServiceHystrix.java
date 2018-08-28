package com.yaochow.user.service.impl;

import com.yaochow.common.HystrixEffectiveEnum;
import com.yaochow.common.ResultBase;
import com.yaochow.dto.UserDTO;
import com.yaochow.user.service.UserService;
import org.springframework.stereotype.Component;

@Component
public class UserServiceHystrix implements UserService {

    @Override
    public ResultBase<UserDTO> insert(UserDTO userDTO) {
        return HystrixEffectiveEnum.NORMAL_EFFECTIVE.getResultBase();
    }

    @Override
    public ResultBase<UserDTO> getUserByAccountId(String accountId) {
        return HystrixEffectiveEnum.NORMAL_EFFECTIVE.getResultBase();
    }

    @Override
    public ResultBase<UserDTO> update(UserDTO userDTO) {
        return HystrixEffectiveEnum.NORMAL_EFFECTIVE.getResultBase();
    }
}
