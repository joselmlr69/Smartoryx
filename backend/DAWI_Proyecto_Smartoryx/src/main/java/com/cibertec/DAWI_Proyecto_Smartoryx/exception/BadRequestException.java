package com.cibertec.DAWI_Proyecto_Smartoryx.exception;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }
}
