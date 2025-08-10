package com.amir.bank.deposit_management_system.common.valueobject;

import com.amir.bank.deposit_management_system.common.exception.InvalidNationalCodeException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import java.io.Serial;
import java.io.Serializable;

public final class NationalCode implements Serializable {
    @Serial private static final long serialVersionUID = 1L;

    @JsonValue
    private final String value;

    private NationalCode(String value) {
        if (!isValid(value)) throw new InvalidNationalCodeException("Invalid national code: " + value);
        this.value = value;
    }

    @JsonCreator
    public static NationalCode of(@JsonProperty("value") String raw) {
        if (raw == null) throw new InvalidNationalCodeException("National code cannot be null");
        String n = raw.replaceAll("[^\\d]", "");
        return new NationalCode(n);
    }

    public String getValue() { return value; }

    public String getMasked() { return value.substring(0,3) + "-******-" + value.substring(9); }

    @Override public String toString() { return "NationalCode(" + getMasked() + ")"; }

    private static boolean isValid(String code) {
        if (code == null || !code.matches("\\d{10}")) return false;
        for (int i=0;i<10;i++) if (code.equals(String.valueOf(i).repeat(10))) return false;
        int sum = 0; for (int i=0;i<9;i++) sum += Character.getNumericValue(code.charAt(i)) * (10 - i);
        int r = sum % 11, c = Character.getNumericValue(code.charAt(9));
        return (r < 2 && c == r) || (r >= 2 && c == (11 - r));
    }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NationalCode other)) return false;
        return value.equals(other.value);
    } @Override public int hashCode() { return value.hashCode(); }
}