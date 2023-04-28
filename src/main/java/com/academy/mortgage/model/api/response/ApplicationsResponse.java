package com.academy.mortgage.model.api.response;

import com.academy.mortgage.model.enums.ApplicationStatus;
import com.academy.mortgage.model.enums.PaymentScheduleType;
import jakarta.persistence.Entity;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.math.BigDecimal;


@Data
@Value
@Builder
public class ApplicationsResponse {
    Long applicationId;
    Integer applicants;
    Integer amountOfKids;
    BigDecimal monthlyIncome;
    BigDecimal coApplicantsIncome;
    Boolean obligations;
    BigDecimal mortgageLoans;
    BigDecimal consumerLoans;
    BigDecimal leasingAmount;
    BigDecimal creditCardLimit;
    BigDecimal monthlyPayment;
    String realEstateAddress;
    BigDecimal realEstatePrice;
    BigDecimal downPayment;
    BigDecimal loanAmount;
    Integer loanTerm;
    PaymentScheduleType paymentScheduleType;
    String firstName;
    String lastName;
    String personalNumber;
    String email;
    String phoneNumber;
    String address;
    BigDecimal interestRateMargin;
    Integer euriborTerm;
    BigDecimal interestRateEuribor;
    BigDecimal totalHouseholdIncome;
    String coApplicantEmail;
    ApplicationStatus applicationStatus;
}
