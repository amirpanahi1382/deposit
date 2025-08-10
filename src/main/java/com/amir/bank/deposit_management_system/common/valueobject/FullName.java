package com.amir.bank.deposit_management_system.common.valueobject;

import com.amir.bank.deposit_management_system.common.exception.InvalidFullNameException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;
import java.util.regex.Pattern;

public final class FullName implements Serializable {
    @Serial private static final long serialVersionUID = 1L;

    private static final int MAX_LENGTH = 50;
    private static final Pattern P = Pattern.compile("^[آ-یءa-zA-Z\\s]+$");

    private final String firstName;
    private final String lastName;

    private FullName(String firstName, String lastName) {
        if (!isValid(firstName) || !isValid(lastName))
            throw new InvalidFullNameException("Invalid first/last name");
        this.firstName = normalize(firstName);
        this.lastName = normalize(lastName);
    }

    @JsonCreator
    public static FullName of(@JsonProperty("firstName") String firstName,
                              @JsonProperty("lastName") String lastName) {
        return new FullName(firstName, lastName);
    }

    public String getFirstName() { return firstName; }
    public String getLastName()  { return lastName; }
    public String getFullName()  { return firstName + " " + lastName; }

    private static String normalize(String s) { return s.trim().replaceAll("\\s{2,}", " "); }
    private static boolean isValid(String s) {
        if (s == null) return false;
        String n = normalize(s);
        return n.length() > 1 && n.length() <= MAX_LENGTH && P.matcher(n).matches();
    }

    @Override public boolean equals(Object o) {   if (this == o) return true;
        if (!(o instanceof FullName other)) return false;
        return firstName.equals(other.firstName) && lastName.equals(other.lastName);
    }

    @Override public int hashCode() { return Objects.hash(firstName, lastName); }
    @Override public String toString() { return "FullName(" + firstName.charAt(0) + "*** " + lastName.charAt(0) + "***)"; }
}