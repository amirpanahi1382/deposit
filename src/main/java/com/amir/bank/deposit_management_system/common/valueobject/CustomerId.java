package com.amir.bank.deposit_management_system.common.valueobject;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * Strongly-typed ID for Customer entity.
 */
public final class CustomerId implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private final UUID value;

    private CustomerId(UUID value) {
        if (value == null) {
            throw new IllegalArgumentException("CustomerId cannot be null");
        }
        this.value = value;
    }

    public static CustomerId newId() {
        return new CustomerId(generateSequentialUUID());
    }
    @JsonCreator
    public static CustomerId of(@JsonProperty("value") UUID value) {
        return new CustomerId(value);
    }
    public UUID getValue() {
        return value;
    }
    @Override
    public String toString() {
        return "CustomerId(" + value.toString() + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CustomerId other)) return false;
        return value.equals(other.value);
    }
    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    // --- بهترین UUID برای index و مرتب‌سازی ---
    private static UUID generateSequentialUUID() {
        long now = System.currentTimeMillis();
        long mostSigBits = now << 16;
        long leastSigBits = UUID.randomUUID().getLeastSignificantBits();
        return new UUID(mostSigBits, leastSigBits);
    }
}
