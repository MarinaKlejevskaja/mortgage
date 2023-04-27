package com.academy.mortgage.model.api.response;

import lombok.Data;

@Data
public class ConstantsResponse {
    private Integer minLoanTerm;
    private Integer maxLoanTerm;
    private Integer maxNumOfApplicants;
    private Float loanAmountPercentage;
    private Float interestRateMargin;
    private Integer maxKids;
    private Integer minKids;
    private Float maxMonthlyObligationsPercentage;
}