package com.amir.bank.deposit_management_system.domain.port.out;

import com.amir.bank.deposit_management_system.domain.model.customer.Customer;
import com.amir.bank.deposit_management_system.common.valueobject.CustomerId;
import com.amir.bank.deposit_management_system.common.valueobject.Email;
import com.amir.bank.deposit_management_system.common.valueobject.NationalCode;
import java.util.Optional;
/**
 * Output Port for accessing Customer persistence layer.
 * This interface is used by the domain/application and implemented by adapters (e.g. JPA, Mongo).
 */
public interface CustomerRepository {
    Customer save(Customer customer);

    Optional<Customer> findById(CustomerId id);
    Optional<Customer> findByEmail(Email email);

    Optional<Customer> findByNationalCode(NationalCode nationalCode);

    boolean existsByEmail(Email email);

    boolean existsByNationalCode(NationalCode nationalCode);

    void delete(CustomerId id);
}