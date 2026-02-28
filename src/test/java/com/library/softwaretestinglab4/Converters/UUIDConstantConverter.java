package com.library.softwaretestinglab4.Converters;

import org.junit.jupiter.params.converter.SimpleArgumentConverter;

import java.util.UUID;

public class UUIDConstantConverter extends SimpleArgumentConverter {
    @Override
    protected Object convert(Object source, Class<?> targetType) {

        if (source == null) return null;

        // Trim whitespace and convert to lowercase for comparison
        String input = source.toString().trim();

        if ("null".equalsIgnoreCase(input) || input.isEmpty()) {
            return null;
        } else {
            return UUID.fromString(input);
        }
    }


}
