package com.academy.mortgage.model.enums;

public enum PaymentScheduleType {
    // annuity, linear
    ANNUITY("Annuity"),
    LINEAR("Linear");
    private String scheduleType;

    PaymentScheduleType(String scheduleType) {

        this.scheduleType = scheduleType;
    }
}
