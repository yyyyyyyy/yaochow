package com.yaochow.parent.common;

public enum ErrorMsgEnum {
    SYSTEM_ERROR("999", "System Error."),
    ACCOUNT_ID_NOT_EXIST_ERROR("", "Landing Timeout"),
    HYSTRIX_ERROR("888", "Hystrix Effective"),
    PASSWORD_INCORRECT_ERROR("777", "Password Incorrect"),
    EMAIL_EXIST_ERROR("666", "Email Exist"),
    USERNAME_EXIST_ERROR("", "Username Exist"),
    ACCOUNT_NOT_EXIST_ERROR("555", "Account Not Exist"),
    REGISTER_FAILED_ERROR("444","Activate Failed"),
    CONFIRM_ERROR("","Confirm Your Email First");

    private String errorCode;
    private String errorMsg;

    ErrorMsgEnum(String errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }
}
