package com.futbol.exceptions;

public class BusinessException extends Exception {

    String code;

    public BusinessException(String message) {
        super(message);
        this.code = "business_exception";
    }
}
