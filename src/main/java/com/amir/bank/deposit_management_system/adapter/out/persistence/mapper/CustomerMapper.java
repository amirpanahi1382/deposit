package com.amir.bank.deposit_management_system.adapter.out.persistence.mapper;

import com.amir.bank.deposit_management_system.adapter.out.persistence.entity.CustomerJpaEntity;
import com.amir.bank.deposit_management_system.common.valueobject.*;
import com.amir.bank.deposit_management_system.domain.model.customer.Customer;

public final class CustomerMapper {
    private CustomerMapper() {}

    public static CustomerJpaEntity toEntity(Customer customer) {
        return new CustomerJpaEntity(
                customer.getId().getValue(),
                customer.getEmail().getValue(),
                customer.getNationalCode().getValue(),
                customer.getPhoneNumber().getValue(),
                customer.getFullName().getFirstName(),
                customer.getFullName().getLastName(),
                customer.getRegisteredAt(),
                customer.isActive()
        );
    }

    public static Customer toDomain(CustomerJpaEntity e) {
        return Customer.restore(
                CustomerId.newId2(e.getId()),
                Email.of(e.getEmail()),
                NationalCode.of(e.getNationalCode()),
                PhoneNumber.of(e.getPhoneNumber()),
                FullName.of(e.getFirstName(), e.getLastName()),
                e.getRegisteredAt(),
                e.isActive()
        );
    }
}