package com.academy.mortgage.exceptions;

public class UserNotFoundException  extends RuntimeException {

    public UserNotFoundException(String email) {
        super("User with email " + email + " not found.");
    }
    public UserNotFoundException(Long id) {
        super("User with id " + id + " not found.");
    }
}
