package com.amir.bank.deposit_management_system.common.exception;

public class InvalidNationalCodeException extends RuntimeException {
    public InvalidNationalCodeException(String message) {
        super(message);
    }
}
