package com.academy.mortgage.model.enums;

public enum PaymentScheduleType {
    // annuity, linear
    ANNUITY("annuity"),
    LINEAR("linear");
    private String scheduleType;

    PaymentScheduleType(String scheduleType) {

        this.scheduleType = scheduleType;
    }
}
