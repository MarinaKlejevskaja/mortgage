package com.academy.mortgage.constants;

import com.academy.mortgage.constants.enums.ApplicationStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "constants")
public class Constants {

    @Id
    @GeneratedValue
    private long id;
    private int minLoanTerm;
    private int maxLoanTerm;
    private int maxNumOfApplicants;
    private float loanAmountPercentage;
    private float interestRateMargin;
    private int maxKids;
    private int minKids;
    private float maxMonthlyObligationsPercentage;
    @Enumerated(EnumType.STRING)
    private ApplicationStatus applicationStatus;
}

