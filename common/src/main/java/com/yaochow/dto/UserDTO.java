package com.yaochow.dto;

import lombok.Data;

import java.io.Serializable;


@Data
public class UserDTO implements Serializable {
    private String id;
    private String accountId;
    private String name;
    private String certificateType;
    private String certificateCode;
    private String age;
    private String gender;
    private String birthday;
}
