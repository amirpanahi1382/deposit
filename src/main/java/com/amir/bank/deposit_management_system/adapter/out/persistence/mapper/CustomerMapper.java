package com.amir.bank.deposit_management_system.adapter.out.persistence.mapper;

import com.amir.bank.deposit_management_system.adapter.out.persistence.entity.CustomerJpaEntity;
import com.amir.bank.deposit_management_system.domain.model.customer.Customer;
import com.amir.bank.deposit_management_system.common.valueobject.*;
public class CustomerMapper {
    public static CustomerJpaEntity toEntity(Customer customer) {
        return new CustomerJpaEntity(
                customer.getId().getValue(),
                customer.getEmail().getValue(),
                customer.getNationalCode().getValue(),
                customer.getPhoneNumber().getValue(),
                customer.getFullName().getFullName(),
                customer.getRegisteredAt(),
                customer.isActive()
                );
    }
    public static Customer toDomain(CustomerJpaEntity entity) {
        return new Customer(
                CustomerId.of(entity.getId())
                ,

                Email.of(entity.getEmail()),
                NationalCode.of(entity.getNationalCode()),
                PhoneNumber.of(entity.getPhoneNumber()),
                FullName.of(entity.getFirstname(),entity.getLastname()),
                entity.getRegisteredAt(),
                entity.isActive()
        );
    }
}
