package com.amir.bank.deposit_management_system.domain.port.in.dto;

import java.time.LocalDateTime;
import java.util.UUID;



public record CustomerResponse(
        UUID id,
        String email,
        String nationalCode,
        String phoneNumber,
        String fullName,
        LocalDateTime registeredAt,
        boolean active
) {}
