package com.amir.bank.deposit_management_system.domain.port.in;
import com.amir.bank.deposit_management_system.domain.port.in.dto.RegisterCustomerCommand;
import com.amir.bank.deposit_management_system.domain.port.in.dto.CustomerResponse;
public interface RegisterCustomerUseCase {
    CustomerResponse register(RegisterCustomerCommand command);
}