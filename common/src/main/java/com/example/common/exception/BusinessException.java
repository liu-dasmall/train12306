package com.example.common.exception;

public class BusinessException extends RuntimeException {

    private BusinessExceptionEnum e;

    public BusinessException(BusinessExceptionEnum e) {
        this.e = e;
    }

    public BusinessExceptionEnum getE() {
        return e;
    }

    public void setE(BusinessExceptionEnum e) {
        this.e = e;
    }

    /**
     * 对于已知的异常，我们不打印具体的堆栈信息，提高性能
     */
    @Override
    public Throwable fillInStackTrace() {
        return this;
    }

}
