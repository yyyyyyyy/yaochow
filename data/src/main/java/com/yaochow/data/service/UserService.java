package com.yaochow.data.service;

import com.yaochow.dto.UserDTO;

public interface UserService {

    UserDTO insert(UserDTO userDTO);

    UserDTO update(UserDTO userDTO);

    UserDTO getUserByAccountId(String accountId);
}
