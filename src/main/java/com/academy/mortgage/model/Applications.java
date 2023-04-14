package com.academy.mortgage.model;

import com.academy.mortgage.model.enums.ApplicationStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "applications")
public class Applications {

    @Id
    @GeneratedValue
    private long id;

//    @OneToOne(fetch = FetchType.LAZY)
//    private Users userId;

    private long monthlyIncome; //max 9223372036854775807
    private long monthlyObligations; //max 9223372036854775807
    private long realEstatePrice; //max 9223372036854775807
    private int downPayment; // min 15% of realEstatePrice
    private long loanAmount; // can't be higher than 85% of realEstatePrice
    private int loanTerm; // 1-30 years
    private float interestRateMargin; // 2.5%
    private float interestRateEuribor; // depends on paymentScheduleType
    private String paymentScheduleType; // annuity, linear
    private int childrenAmount;
    private int applicantsAmount;
    @Enumerated(EnumType.STRING)
    private ApplicationStatus applicationStatus;
}
