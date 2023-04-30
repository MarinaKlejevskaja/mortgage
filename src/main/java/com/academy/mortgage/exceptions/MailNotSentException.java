package com.academy.mortgage.exceptions;

public class MailNotSentException  extends RuntimeException {
    public MailNotSentException(String message) {
        super(message);
    }
}