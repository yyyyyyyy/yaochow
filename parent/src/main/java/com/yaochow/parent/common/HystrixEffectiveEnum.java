package com.yaochow.parent.common;

public enum HystrixEffectiveEnum {

    NORMAL_EFFECTIVE(false, ErrorMsgEnum.HYSTRIX_ERROR.getErrorCode(), ErrorMsgEnum.HYSTRIX_ERROR.getErrorMsg());

    private ResultBase resultBase;

    HystrixEffectiveEnum(boolean success, String errorCode, String errorMsg) {
        this.resultBase = new ResultBase();
        this.resultBase.setSuccess(success);
        this.resultBase.setErrorCode(errorCode);
        this.resultBase.setErrorMsg(errorMsg);
    }

    public ResultBase getResultBase(){
        return this.resultBase;
    }
}
