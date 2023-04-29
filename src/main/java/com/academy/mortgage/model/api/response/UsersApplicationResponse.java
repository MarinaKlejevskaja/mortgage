package com.academy.mortgage.model.api.response;

import com.academy.mortgage.model.enums.ApplicationStatus;
import com.academy.mortgage.model.enums.PaymentScheduleType;
import lombok.Builder;
import lombok.Data;
import lombok.Value;

import java.math.BigDecimal;

@Data
@Value
@Builder
public class UsersApplicationResponse {
    Long applicationId;
    String realEstateAddress;
    BigDecimal realEstatePrice;
    BigDecimal downPayment;
    BigDecimal loanAmount;
    Integer loanTerm;
    PaymentScheduleType paymentScheduleType;
    Integer euriborTerm;
    BigDecimal interestRateEuribor;
    ApplicationStatus applicationStatus;
}