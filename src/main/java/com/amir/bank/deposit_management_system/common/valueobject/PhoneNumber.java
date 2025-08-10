package com.amir.bank.deposit_management_system.common.valueobject;

import com.amir.bank.deposit_management_system.common.exception.InvalidPhoneNumberException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import java.io.Serial;
import java.io.Serializable;
import java.util.regex.Pattern;

public final class PhoneNumber implements Serializable {
    @Serial private static final long serialVersionUID = 1L;

    private static final Pattern ONLY_DIGITS_11 = Pattern.compile("^\\d{11}$");

    @JsonValue
    private final String value; // normalized: 09xxxxxxxxx

    private PhoneNumber(String value) {
        if (!isValid(value)) throw new InvalidPhoneNumberException("Invalid phone number: " + value);
        this.value = value;
    }

    @JsonCreator
    public static PhoneNumber of(@JsonProperty("value") String raw) {
        if (raw == null) throw new InvalidPhoneNumberException("Phone number cannot be null");
        String normalized = normalize(raw);
        return new PhoneNumber(normalized);
    }

    public String getValue() { return value; }

    public String getMasked() { return value.substring(0,4) + "***" + value.substring(7); }

    @Override public String toString() { return "PhoneNumber(" + getMasked() + ")"; }

    private static String normalize(String raw) {
        String digits = raw.replaceAll("[^\\d]","");
        if (digits.startsWith("0098")) digits = "0" + digits.substring(4);
        else if (digits.startsWith("98")) digits = "0" + digits.substring(2);
        if (digits.length() == 10 && digits.startsWith("9")) digits = "0" + digits;
        return digits;
    }
    private static boolean isValid(String n) {
        return n != null && ONLY_DIGITS_11.matcher(n).matches() && n.startsWith("09");
    }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PhoneNumber other)) return false;
        return value.equals(other.value);
    }

    @Override public int hashCode() { return value.hashCode(); }
}