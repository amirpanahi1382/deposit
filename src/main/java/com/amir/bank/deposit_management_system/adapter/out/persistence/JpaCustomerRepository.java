package com.amir.bank.deposit_management_system.adapter.out.persistence;

import com.amir.bank.deposit_management_system.adapter.out.persistence.entity.CustomerJpaEntity;
import com.amir.bank.deposit_management_system.adapter.out.persistence.mapper.CustomerMapper;
import com.amir.bank.deposit_management_system.adapter.out.persistence.repository.SpringDataCustomerRepository;
import com.amir.bank.deposit_management_system.common.valueobject.CustomerId;
import com.amir.bank.deposit_management_system.domain.model.customer.Customer;
import com.amir.bank.deposit_management_system.domain.port.out.CustomerRepository;
import com.amir.bank.deposit_management_system.common.valueobject.Email;
import com.amir.bank.deposit_management_system.common.valueobject.NationalCode;

import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class JpaCustomerRepository implements CustomerRepository {

    private final SpringDataCustomerRepository repo;

    public JpaCustomerRepository(SpringDataCustomerRepository repo) {
        this.repo = repo;
    }

    @Override
    public Customer save(Customer customer) {
        CustomerJpaEntity entity = CustomerMapper.toEntity(customer);
        CustomerJpaEntity saved = repo.save(entity);
        return CustomerMapper.toDomain(saved);
    }

    @Override
    public Optional<Customer> findById(CustomerId id) {
        return repo.findById(id.getValue()).map(CustomerMapper::toDomain);
    }

    @Override
    public Optional<Customer> findByEmail(Email email) {
        return repo.findByEmail(email.getValue()).map(CustomerMapper::toDomain);
    }

    @Override
    public Optional<Customer> findByNationalCode(NationalCode nationalCode) {
        return repo.findByNationalCode(nationalCode.getValue()).map(CustomerMapper::toDomain);
    }

    @Override
    public boolean existsByEmail(Email email) {
        return repo.existsByEmail(email.getValue());
    }

    @Override
    public boolean existsByNationalCode(NationalCode nationalCode) {
        return repo.existsByNationalCode(nationalCode.getValue());
    }

    @Override
    public void delete(CustomerId id) {
        repo.deleteById(id.getValue());
    }
}