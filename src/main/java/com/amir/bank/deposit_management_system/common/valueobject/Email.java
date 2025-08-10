package com.amir.bank.deposit_management_system.common.valueobject;

import com.amir.bank.deposit_management_system.common.exception.InvalidEmailException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import org.apache.commons.validator.routines.EmailValidator;

import java.io.Serial;
import java.io.Serializable;

public final class Email implements Serializable {
    @Serial private static final long serialVersionUID = 1L;
    private static final EmailValidator VALIDATOR = EmailValidator.getInstance();

    @JsonValue
    private final String value;

    private Email(String value) {
        if (value == null || !VALIDATOR.isValid(value)) {
            throw new InvalidEmailException("Invalid email format: " + value);
        }
        this.value = value;
    }

    @JsonCreator
    public static Email of(@JsonProperty("value") String rawValue) {
        if (rawValue == null) throw new InvalidEmailException("Email cannot be null");
        String normalized = rawValue.trim().toLowerCase();
        return new Email(normalized);
    }

    public String getValue() { return value; }

    public boolean isGmail() { return value.endsWith("@gmail.com"); }

    public String getDomain() {
        int at = value.indexOf('@');
        return at >= 0 ? value.substring(at + 1) : "";
    }
    @Override public String toString() {
        int at = value.indexOf('@');
        if (at <= 1) return "Email(**masked**)";
        String u = value.substring(0, at);
        String masked = (u.length() <= 2 ? u.charAt(0) : u.substring(0,2)) + "***";
        return "Email(" + masked + "@***.***)";
    }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Email other)) return false;
        return value.equals(other.value);
    }

    @Override public int hashCode() { return value.hashCode(); }
}