package com.amir.bank.deposit_management_system.adapter.in.rest;

import com.amir.bank.deposit_management_system.domain.port.in.RegisterCustomerUseCase;
import com.amir.bank.deposit_management_system.domain.port.in.dto.CustomerResponse;
import com.amir.bank.deposit_management_system.domain.port.in.dto.RegisterCustomerCommand;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")
@Validated
public class CustomerController {

    private record RegisterRequest(
            @NotBlank String email,
            @NotBlank String nationalCode,
            @NotBlank String phoneNumber,
            @NotBlank String firstName,
            @NotBlank String lastName
    ) {}

    private final RegisterCustomerUseCase useCase;  public CustomerController(RegisterCustomerUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping
    public ResponseEntity<CustomerResponse> register(@RequestBody RegisterRequest req) {
        CustomerResponse resp = useCase.register(new RegisterCustomerCommand(
                req.email(), req.nationalCode(), req.phoneNumber(), req.firstName(), req.lastName()
        ));
        return ResponseEntity.status(HttpStatus.CREATED).body(resp);
    }
}