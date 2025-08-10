package com.amir.bank.deposit_management_system.adapter.in.rest;

import com.amir.bank.deposit_management_system.common.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private ResponseEntity<Map<String, Object>> badReq(String message) {
        return ResponseEntity.badRequest().body(Map.of(
                "error", message,
                "status", 400
        ));
    }

    @ExceptionHandler({InvalidEmailException.class, InvalidPhoneNumberException.class,
            InvalidNationalCodeException.class, InvalidFullNameException.class, IllegalArgumentException.class})
    public ResponseEntity<Map<String, Object>> handleBadRequest(RuntimeException ex) {
        return badReq(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleAny(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of("error", "Internal error", "status", 500));
    }
}