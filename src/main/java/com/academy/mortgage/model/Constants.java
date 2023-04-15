package com.academy.mortgage.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
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
}

