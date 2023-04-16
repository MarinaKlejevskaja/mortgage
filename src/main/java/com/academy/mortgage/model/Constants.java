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
    private Long id;
    private Integer minLoanTerm;
    private Integer maxLoanTerm;
    private Integer maxNumOfApplicants;
    private Float loanAmountPercentage;
    private Float interestRateMargin;
    private Integer maxKids;
    private Integer minKids;
    private Float maxMonthlyObligationsPercentage;
}

