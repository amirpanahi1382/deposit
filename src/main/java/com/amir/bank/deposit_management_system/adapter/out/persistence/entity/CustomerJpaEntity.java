package com.amir.bank.deposit_management_system.adapter.out.persistence.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "customers",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_customers_email", columnNames = "email"),
                @UniqueConstraint(name = "uk_customers_national_code", columnNames = "national_code"),
                @UniqueConstraint(name = "uk_customers_phone_number", columnNames = "phone_number")
        })
public class CustomerJpaEntity {

    @Id
    private UUID id;

    @Column(nullable = false)
    private String email;

    @Column(name = "national_code", nullable = false)
    private String nationalCode;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;

    @Column(name = "registered_at", nullable = false)
    private LocalDateTime registeredAt; @Column(nullable = false)
    private boolean active;

    protected CustomerJpaEntity() {}

    public CustomerJpaEntity(UUID id, String email, String nationalCode, String phoneNumber,
                             String firstName, String lastName, LocalDateTime registeredAt,
                             boolean active) {
        this.id = id;
        this.email = email;
        this.nationalCode = nationalCode;
        this.phoneNumber = phoneNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.registeredAt = registeredAt;
        this.active = active;
    }

    public UUID getId() { return id; }
    public String getEmail() { return email; }
    public String getNationalCode() { return nationalCode; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public LocalDateTime getRegisteredAt() { return registeredAt; }
    public boolean isActive() { return active; }
}