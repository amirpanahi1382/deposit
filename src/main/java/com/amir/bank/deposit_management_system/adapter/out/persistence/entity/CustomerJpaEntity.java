package com.amir.bank.deposit_management_system.adapter.out.persistence.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "customers")
public class CustomerJpaEntity {

    @Id
    private UUID id;

    private String email;
    private String nationalCode;
    private String phoneNumber;
    private String firstname;
private String lastname;
    private LocalDateTime registeredAt;
    private boolean active;

    protected CustomerJpaEntity() {} // for JPA

    public CustomerJpaEntity(UUID id, String email, String nationalCode, String phoneNumber,
                             String fisrtname,String lastname, LocalDateTime registeredAt, boolean active) {
        this.id = id;
        this.email = email;
        this.nationalCode = nationalCode;
        this.phoneNumber = phoneNumber;
        this.firstname=fisrtname;
        this.lastname=lastname;
        this.registeredAt = registeredAt;
        this.active = active;
    }

    // Getters
    public UUID getId() { return id; }
    public String getEmail() { return email; }
    public String getNationalCode() { return nationalCode; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getFullName() { return firstname+""+lastname; }
    public String getFirstname(){
        return firstname;
    }
    public String getLastname(){
        return lastname;
    }
    public LocalDateTime getRegisteredAt() { return registeredAt; }
    public boolean isActive() { return active; }
}
