package com.academy.mortgage.model.api.request;


import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ConstantsRequest {

    @Min(value = 1, message = "Minimum minLoanTerm must be at least 1 year")
    @Max(value = 5, message = "Maximum loan term cannot exceed 30 years")
    private Integer minLoanTerm;

    @Min(value = 5, message = "Minimum maxLoanTerm must be at least 1 year")
    @Max(value = 100, message = "Maximum loan term cannot exceed 100 years")
    private Integer maxLoanTerm;

    @Min(value = 1, message = "Maximum number of applicants cannot be less than 1")
    @Max(value = 5, message = "Maximum number of applicants cannot exceed 5")
    private Integer maxNumOfApplicants;

    @DecimalMin(value = "0.00", inclusive = true, message = "Loan amount percentage cannot be negative")
    @DecimalMax(value = "1.00", inclusive = true, message = "Loan amount percentage cannot exceed 100%")
    private Float loanAmountPercentage;

    @DecimalMin(value = "0.00", inclusive = true, message = "Interest rate margin cannot be negative")
    private Float interestRateMargin;

    @Min(value = 0, message = "Minimum number of kids cannot be negative")
    private Integer minKids;

    @Min(value = 0, message = "Maximum number of kids cannot be negative")
    private Integer maxKids;

    @DecimalMin(value = "0.00", inclusive = true, message = "Maximum monthly obligations percentage cannot be negative")
    @DecimalMax(value = "1.00", inclusive = true, message = "Maximum monthly obligations percentage cannot exceed 100%")
    private Float maxMonthlyObligationsPercentage;
}