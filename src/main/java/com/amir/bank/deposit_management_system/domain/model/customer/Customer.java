package com.amir.bank.deposit_management_system.domain.model.customer;
import com.amir.bank.deposit_management_system.common.valueobject.*;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Objects;
/**
 * Domain Entity representing a Customer (pure, immutable, and clean).
 */



public final class Customer {
    private final CustomerId id;
    private final Email email;
    private final NationalCode nationalCode;
    private final PhoneNumber phoneNumber;
    private final FullName fullName;
    private final LocalDateTime registeredAt;
    private final boolean active;
    private Customer(CustomerId id, Email email, NationalCode nationalCode,
                     PhoneNumber phoneNumber, FullName fullName,
                     LocalDateTime registeredAt, boolean active) {
        this.id = id;
        this.email = email;
        this.nationalCode = nationalCode;
        this.phoneNumber = phoneNumber;
        this.fullName = fullName;
        this.registeredAt = registeredAt;
        this.active = active;
    }

    public static Customer restore(
            CustomerId id,
            Email email,
            NationalCode nationalCode,
            PhoneNumber phoneNumber,
            FullName fullName,
            LocalDateTime registeredAt,
            boolean active
    ) {
        if (id == null) {
            throw new IllegalArgumentException("Customer ID cannot be null");
        }
        if (email == null) {
            throw new IllegalArgumentException("Email cannot be null");
        }
        if (nationalCode == null) {
            throw new IllegalArgumentException("National code cannot be null");
        }
        if (phoneNumber == null) {
            throw new IllegalArgumentException("Phone number cannot be null");
        }
        if (fullName == null) {
            throw new IllegalArgumentException("Full name cannot be null");
        }
        if (registeredAt == null) {
            throw new IllegalArgumentException("Registered date cannot be null");
        }

        return new Customer(id, email, nationalCode, phoneNumber, fullName, registeredAt, active);
    }

    /**
     * Factory method for registering a new customer.
     */
    public static Customer registerNew(Email email, NationalCode nationalCode,
                                       PhoneNumber phoneNumber, FullName fullName) {
        return new Customer(
                CustomerId.newId(),
                email,
                nationalCode,
                phoneNumber,
                fullName,
                LocalDateTime.now(),
                true
        );
    }
    /**
     * Returns a new instance with status deactivated.
     */
    public Customer deactivate() {
        return new Customer(id, email, nationalCode, phoneNumber, fullName, registeredAt, false);
    }
    /**
     * Returns a new instance with status activated.
     */
    public Customer activate() {

        return new Customer(id, email, nationalCode, phoneNumber, fullName, registeredAt, true);
    }
    // --- Getters ---
    public CustomerId getId() {
        return id;
    }
    public Email getEmail() {
        return email;
    }
    public NationalCode getNationalCode() {
        return nationalCode;
    }
    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }
    public FullName getFullName() {
        return fullName;
    }
    public LocalDateTime getRegisteredAt() {
        return registeredAt;
    }
    public boolean isActive() {
        return active;
    }

    // --- Equality by ID ---

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer other)) return false;
        return id.equals(other.id);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}