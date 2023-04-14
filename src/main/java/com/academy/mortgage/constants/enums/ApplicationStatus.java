package com.academy.mortgage.constants.enums;

public enum ApplicationStatus {
    RECEIVED("Received"),
    IN_PROGRESS("In Progress"),
    APPROVED("Approved"),
    REJECTED("Rejected");

    private String status;

    ApplicationStatus(String status) {
        this.status = status;
    }
}
