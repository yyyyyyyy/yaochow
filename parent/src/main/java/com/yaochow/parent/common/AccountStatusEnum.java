package com.yaochow.parent.common;

public enum AccountStatusEnum {

    REGISTER("0", "register"),
    CONFIRM("1", "confirm"),
    CANCEL("2", "cancel");


    private String code;
    private String desc;

    AccountStatusEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
