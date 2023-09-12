package com.btk.exception;

import lombok.Getter;

@Getter
public class SaleManagerException extends RuntimeException{

    private final ErrorType errorType;

    public SaleManagerException(ErrorType errorType, String customMessage) {
        super(customMessage);
        this.errorType = errorType;
    }

    public SaleManagerException(ErrorType errorType){
        this.errorType = errorType;
    }
}
