package com.amir.bank.deposit_management_system.common.valueobject;

import com.amir.bank.deposit_management_system.domain.model.customer.Customer;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

public final class CustomerId implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    private final UUID value;

    private CustomerId(UUID value) {
        if (value == null) {
            throw new IllegalArgumentException("Customer ID cannot be null");
        }
        this.value = value;
    }

    public static CustomerId newId() {
        return new CustomerId(generateSequentialUUID());
    }
    public static CustomerId newId2(UUID value) {
        return  new CustomerId(value);
    }

    public UUID getValue() {
        return value;
    }

    private static UUID generateSequentialUUID() {
        long timestamp = System.currentTimeMillis();
        long timeHigh = (timestamp & 0xFFFFFFFFFFFF0000L) << 16; // high bits
        long timeLow = (timestamp & 0x000000000000FFFFL) << 48;  // low bits
        long randomBits = UUID.randomUUID().getLeastSignificantBits() & 0x0000FFFFFFFFFFFFL;
        long mostSigBits = timeHigh | timeLow | 0x0000000000007000L; // version 7 indicator bits
        return new UUID(mostSigBits, randomBits);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CustomerId)) return false;
        return value.equals(((CustomerId) o).value);
    } @Override
    public int hashCode() {
        return value.hashCode();
    }
}