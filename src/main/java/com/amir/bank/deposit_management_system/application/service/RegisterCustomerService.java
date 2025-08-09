package com.amir.bank.deposit_management_system.application.service;
import com.amir.bank.deposit_management_system.domain.model.customer.Customer;
import com.amir.bank.deposit_management_system.domain.port.in.RegisterCustomerUseCase;
import com.amir.bank.deposit_management_system.domain.port.in.dto.RegisterCustomerCommand;
import com.amir.bank.deposit_management_system.domain.port.in.dto.CustomerResponse;
import com.amir.bank.deposit_management_system.domain.port.out.CustomerRepository;
import com.amir.bank.deposit_management_system.common.valueobject.*;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class RegisterCustomerService implements RegisterCustomerUseCase {
    private final CustomerRepository customerRepository;
    public RegisterCustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    @Override
    @Transactional
    public CustomerResponse register(RegisterCustomerCommand command) {
        // تبدیل مقادیر خام به Value Object
        Email email = Email.of(command.email());
        NationalCode nationalCode = NationalCode.of(command.nationalCode());
        PhoneNumber phoneNumber = PhoneNumber.of(command.phoneNumber());
        FullName fullName = FullName.of(command.firstName(), command.lastName());
        // چک برای عدم تکرار

        if (customerRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("ایمیل وارد شده قبلاً ثبت شده است");
        }

        if (customerRepository.existsByNationalCode(nationalCode)) {
            throw new IllegalArgumentException("کد ملی وارد شده قبلاً ثبت شده است");
        }

        // ایجاد موجودیت جدید مشتری
        Customer customer = Customer.registerNew(email, nationalCode, phoneNumber, fullName);

        Customer saved = customerRepository.save(customer);

        // تبدیل به DTO برای پاسخ
        return new CustomerResponse(
                saved.getId().getValue(),
                saved.getEmail().getValue(),
                saved.getNationalCode().getValue(),
                saved.getPhoneNumber().getValue(),
                saved.getFullName().getFullName(),
                saved.getRegisteredAt(),
                saved.isActive()
        );

    }
}
