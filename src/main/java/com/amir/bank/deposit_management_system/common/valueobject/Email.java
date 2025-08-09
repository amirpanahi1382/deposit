package com.amir.bank.deposit_management_system.common.valueobject;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import org.apache.commons.validator.routines.EmailValidator;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

/**
 * Value Object representing a validated, immutable Email address.
 * Features:
 * - Full immutability
 * - Controlled instantiation (factory method only)
 * - Validation with Apache Commons EmailValidator
 * - Normalization (trim + lowercase)
 * - Domain-specific helpers (isGmail, getDomain, etc.)
 * - Masked toString() for safe logging
 */
public final class Email implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private static final EmailValidator VALIDATOR = EmailValidator.getInstance();

    @JsonValue
    private final String value;

    /**
     * Private constructor — only accessible via factory method.
     */
    private Email(String value) {
        if (value == null || !VALIDATOR.isValid(value)) {
            throw new IllegalArgumentException("Invalid email format: " + value);
        }
        this.value = value;
    }

    /**
     * Factory method with normalization and validation.
     */
    @JsonCreator
    public static Email of(@JsonProperty("value") String rawValue) {
        if (rawValue == null) {
            throw new IllegalArgumentException("Email cannot be null");
        }

        String normalized = rawValue.trim().toLowerCase();
        return new Email(normalized); // validation happens here
    }

    public String getValue() {
        return value;
    }

    public boolean isGmail() {
        return value.endsWith("@gmail.com");
    }

    public boolean isCorporate() {
        return !(value.endsWith("@gmail.com")
                || value.endsWith("@yahoo.com")
                || value.endsWith("@outlook.com")
                || value.endsWith("@hotmail.com"));
    }

    public String getDomain() {
        int atIndex = value.indexOf('@');
        return (atIndex != -1) ? value.substring(atIndex + 1) : "";
    }



    public String getUsername() {
        int atIndex = value.indexOf('@');
        return (atIndex != -1) ? value.substring(0, atIndex) : value;
    }

    @Override
    public String toString() {
        int atIndex = value.indexOf('@');
        if (atIndex <= 0) return "Email(**masked**)";
        String username = getUsername();
        if (username.length() < 2) return "Email(**masked**)";
        String maskedUsername = username.substring(0, 2) + "***";
        return "Email(" + maskedUsername + "@***.***)";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Email other)) return false;
        return value.equals(other.value);
    }
    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}