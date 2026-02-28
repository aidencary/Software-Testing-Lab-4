package com.library.softwaretestinglab4.Converters;

import org.junit.jupiter.params.converter.SimpleArgumentConverter;

import java.util.UUID;

public class UUIDConstantConverter extends SimpleArgumentConverter {
    @Override
    protected Object convert(Object source, Class<?> targetType) {
        if ("null".equals(source)) {
            return null;
        }
        else {
            return UUID.fromString(source.toString());
        }
    }


}
