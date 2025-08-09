package com.amir.bank.deposit_management_system.common.valueobject;

import com.amir.bank.deposit_management_system.common.exception.InvalidFullNameException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;
import java.util.regex.Pattern;

/**
 * Value Object representing a validated, normalized full name (first + last).
 */
public final class FullName implements Serializable {




    @Serial
    private static final long serialVersionUID = 1L;

    private static final int MAX_LENGTH = 30;
    private static final Pattern VALID_NAME_PATTERN = Pattern.compile("^[آ-یءa-zA-Z\\s]+$");

    private final String firstName;
    private final String lastName;

    private FullName(String firstName, String lastName) {
        if (!isValid(firstName) || !isValid(lastName)) {
            throw new InvalidFullNameException("Invalid first name or last name");
        }

        this.firstName = normalize(firstName);
        this.lastName = normalize(lastName);
    }

    @JsonCreator
    public static FullName of(
            @JsonProperty("firstName") String firstName,
            @JsonProperty("lastName") String lastName) {
        return new FullName(firstName, lastName);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public String getMasked() {
        return firstName.charAt(0) + "*** " + lastName.charAt(0) + "***";
    }

    @Override
    public String toString() {
        return "FullName(" + getMasked() + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FullName other)) return false;
        return firstName.equals(other.firstName) && lastName.equals(other.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }

    // ------------ Validation Logic ------------



    
    private static boolean isValid(String name) {
        if (name == null) return false;
        String normalized = normalize(name);
        return normalized.length() > 1 &&
                normalized.length() <= MAX_LENGTH &&
                VALID_NAME_PATTERN.matcher(normalized).matches();
    }
    private static String normalize(String name) {
        return name.trim().replaceAll("\\s{2,}", " "); // حذف فاصله‌های اضافی
    }
}
