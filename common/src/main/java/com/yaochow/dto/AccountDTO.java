package com.yaochow.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class AccountDTO implements Serializable {
    private String id;
    private String username;
    private String password;
    private String email;
    private String status;
}
