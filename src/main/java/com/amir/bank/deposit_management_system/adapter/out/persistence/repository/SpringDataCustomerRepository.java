package com.amir.bank.deposit_management_system.adapter.out.persistence.repository;

import com.amir.bank.deposit_management_system.adapter.out.persistence.entity.CustomerJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SpringDataCustomerRepository extends JpaRepository<CustomerJpaEntity, UUID> {
    Optional<CustomerJpaEntity> findByEmail(String email);
    Optional<CustomerJpaEntity> findByNationalCode(String nationalCode);
    boolean existsByEmail(String email);
    boolean existsByNationalCode(String nationalCode);
}