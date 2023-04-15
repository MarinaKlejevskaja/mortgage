package com.academy.mortgage.exceptions;

public class ConstantsNotFoundException extends RuntimeException {

    public ConstantsNotFoundException(Long id) {
        super("Constants with ID " + id + " not found.");
    }
}
