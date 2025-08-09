package com.amir.bank.deposit_management_system.domain.port.in.dto;

public record RegisterCustomerCommand(
        String email,
        String nationalCode,
        String phoneNumber,
        String firstName,
        String lastName
) {}
