package com.academy.mortgage.model;

import com.academy.mortgage.model.enums.ApplicationStatus;
import com.academy.mortgage.model.enums.PaymentScheduleType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "applications")
public class Applications {

    @Id
    @GeneratedValue
    private Long applicationId;
    @JoinColumn(name = "user_id")
    private Long userId;
    private Long coApplicantId;
    private Float monthlyIncome;
    private Float mortgageLoans;
    private Float consumerLoans;
    private Float leasingAmount;
    private Float creditCardLimit;
    private Float realEstatePrice;
    private Float downPayment;
    private Float loanAmount;
    private Integer loanTerm;
    private Float interestRateMargin;
    private Float interestRateEuribor;
    @Enumerated(EnumType.STRING)
    private PaymentScheduleType paymentScheduleType;
    private Integer childrenAmount;
    private Integer applicantsAmount;
    @Enumerated(EnumType.STRING)
    private ApplicationStatus applicationStatus;
}
