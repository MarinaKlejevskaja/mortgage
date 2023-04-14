package com.academy.mortgage.model.enums;

public enum ApplicationStatus {
    RECEIVED("received"),
    IN_PROGRESS("In Progress"),
    APPROVED("Approved"),
    REJECTED("Rejected");

    private String status;

    ApplicationStatus(String status) {
        this.status = status;
    }
}
