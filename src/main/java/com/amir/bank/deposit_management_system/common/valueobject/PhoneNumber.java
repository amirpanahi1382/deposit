package com.amir.bank.deposit_management_system.common.valueobject;

import com.amir.bank.deposit_management_system.common.exception.InvalidPhoneNumberException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;
import java.util.regex.Pattern;

/**
 * Value Object representing a validated, normalized Iranian phone number.
 */
public final class PhoneNumber implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private static final Pattern ONLY_DIGITS = Pattern.compile("^\\d{11}$");

    @JsonValue
    private final String value; // Always stored as normalized: 09xxxxxxxxx

    private PhoneNumber(String value) {
        if (!isValid(value)) {
            throw new InvalidPhoneNumberException("Invalid phone number: " + value);
        }
        this.value = value;
    }

    @JsonCreator
    public static PhoneNumber of(@JsonProperty("value") String rawValue) {
        if (rawValue == null) {
            throw new InvalidPhoneNumberException("Phone number cannot be null");
        }

        String normalized = normalize(rawValue);
        return new PhoneNumber(normalized);
    }

    public String getValue() {
        return value;
    }

    public String getMasked() {
        return value.substring(0, 4) + "***" + value.substring(7);
    }

    public boolean isIrancell() {
        return value.startsWith("093") || value.startsWith("0901") || value.startsWith("0902") || value.startsWith("0903");
    }

    public boolean isHamrahAval() {
        return value.startsWith("091") || value.startsWith("0990") || value.startsWith("0991");
    }

    public boolean isRightel() {
        return value.startsWith("0920") || value.startsWith("0921");
    }

    @Override
    public String toString() {
        return "PhoneNumber(" + getMasked() + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PhoneNumber other)) return false;
        return value.equals(other.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    // ------------- Internal logic -------------

    private static String normalize(String raw) {
        String digitsOnly = raw.replaceAll("[^\\d]", "");

        if (digitsOnly.startsWith("0098")) {
            digitsOnly = "0" + digitsOnly.substring(4);
        } else if (digitsOnly.startsWith("98")) {
            digitsOnly = "0" + digitsOnly.substring(2);
        }

        if (digitsOnly.length() == 10 && digitsOnly.startsWith("9")) {
            digitsOnly = "0" + digitsOnly; // e.g., 9123456789 → 09123456789
        }

        return digitsOnly;
    }

    private static boolean isValid(String normalized) {
        if (normalized == null) return false;
        if (!ONLY_DIGITS.matcher(normalized).matches()) return false;
        if (!normalized.startsWith("09")) return false;
        return true;
    }
}
