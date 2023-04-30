package com.academy.mortgage.exceptions;

public class ApplicationNotSavedException extends RuntimeException{
    public ApplicationNotSavedException(String message) {
        super(message);
    }
}
