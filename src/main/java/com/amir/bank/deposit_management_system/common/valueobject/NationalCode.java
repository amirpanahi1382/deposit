package com.amir.bank.deposit_management_system.common.valueobject;

import com.amir.bank.deposit_management_system.common.exception.InvalidNationalCodeException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

/**
 * Value Object representing a validated, normalized Iranian National Code.
 */
public final class NationalCode implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @JsonValue
    private final String value;

    private NationalCode(String value) {
        if (!isValid(value)) {
            throw new InvalidNationalCodeException("Invalid national code: " + value);
        }
        this.value = value;
    }

    @JsonCreator
    public static NationalCode of(@JsonProperty("value") String rawValue) {
        if (rawValue == null) {
            throw new InvalidNationalCodeException("National code cannot be null");
        }

        String normalized = normalize(rawValue);
        return new NationalCode(normalized);
    }
    public String getValue() {
        return value;
    }
    public boolean isMale() {
        if (value.length() != 10) {
            throw new IllegalStateException("Cannot determine gender from invalid national code");
        }
        int genderDigit = Character.getNumericValue(value.charAt(9));
        return genderDigit % 2 == 1;
    }
    public boolean isFemale() {
        return !isMale();
    }

    public String getMasked() {
        return value.substring(0, 3) + "-******-" + value.substring(9);
    }

    @Override
    public String toString() {
        return "NationalCode(" + getMasked() + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NationalCode other)) return false;
        return value.equals(other.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    // ---------- Internal Helpers ----------
    
    private static String normalize(String raw) {
        return raw.replaceAll("[^\\d]", ""); // Remove all non-digit chars
    }

    private static boolean isValid(String code) {
        if (code == null || !code.matches("\\d{10}")) return false;

        // Reject repeated codes like 0000000000, 1111111111, etc.
        for (int i = 0; i < 10; i++) {
            if (code.equals(String.valueOf(i).repeat(10))) return false;
        }

        int sum = 0;
        for (int i = 0; i < 9; i++) {
            sum += Character.getNumericValue(code.charAt(i)) * (10 - i);
        }

        int remainder = sum % 11;
        int checkDigit = Character.getNumericValue(code.charAt(9));

        return (remainder < 2 && checkDigit == remainder)
                || (remainder >= 2 && checkDigit == (11 - remainder));
    }
}
