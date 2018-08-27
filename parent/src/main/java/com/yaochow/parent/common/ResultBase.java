package com.yaochow.parent.common;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResultBase<T> implements Serializable {

    private boolean success;

    private T result;

    private String errorCode;

    private String errorMsg;

    public ResultBase(){}

    public ResultBase(boolean success, String errorCode, String errorMsg){
        super();
        this.success = success;
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }
}
