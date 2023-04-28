package com.academy.mortgage.model.api.request;

import com.academy.mortgage.model.enums.PaymentScheduleType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationRequest {
    @NotBlank
    @Email
    @Schema(description = "User email", example = "jane.doe@example.com")
    private String email;

    @NotBlank
    @Size(min = 1, max = 50)
    @Schema(description = "User first name", example = "Jane")
    private String firstName;

    @NotBlank
    @Size(min = 1, max = 50)
    @Schema(description = "User last name", example = "Doe")
    private String lastName;

    @NotBlank
    @Schema(description = "User personal number", example = "39101010001")
    private String personalNumber;

    @NotBlank
    @Schema(description = "User phone number", example = "860021314")
    private String phoneNumber;

    @NotBlank
    @Size(min = 1, max = 100)
    @Schema(description = "User address", example = "123 Main St")
    private String address;

    @NotNull
    @PositiveOrZero
    @Schema(description = "Amount of kids", example = "2")
    private Integer amountOfKids;

    @NotNull
    @PositiveOrZero
    @Schema(description = "Applicants count", example = "1")
    private Integer applicants;

    @NotNull
    @PositiveOrZero
    @Schema(description = "Monthly income", example = "5000.0")
    private BigDecimal monthlyIncome;

    @PositiveOrZero
    @Schema(description = "Monthly income", example = "5000.0")
    private BigDecimal coApplicantsIncome;

    @NotNull
    @PositiveOrZero
    @Schema(description = "Monthly income", example = "5000.0")
    private BigDecimal totalHouseholdIncome;

    @NotNull
    @Schema(description = "Does user have obligations", example = "true")
    private Boolean obligations;

    @PositiveOrZero
    @Schema(description = "Mortgage loans amount", example = "100000.0")
    private BigDecimal mortgageLoans;

    @PositiveOrZero
    @Schema(description = "Consumer loans amount", example = "10000.0")
    private BigDecimal consumerLoans;

    @PositiveOrZero
    @Schema(description = "Leasing amount", example = "0.0")
    private BigDecimal leasingAmount;

    @PositiveOrZero
    @Schema(description = "Credit card limit", example = "5000.0")
    private BigDecimal creditCardLimit;

    @NotNull
    @Positive
    @Schema(description = "Monthly payment amount", example = "1500.0")
    private BigDecimal monthlyPayment;

    @NotBlank
    @Size(min = 1, max = 255)
    @Schema(description = "Real Estate Address", example = "123 Main St")
    private String realEstateAddress;

    @NotNull
    @Positive
    @Schema(description = "Real estate price", example = "250000.0")
    private BigDecimal realEstatePrice;

    @NotNull
    @Positive
    @Schema(description = "Down payment amount", example = "20000.0")
    private BigDecimal downPayment;

    @NotNull
    @Positive
    @Schema(description = "Loan amount", example = "200000.0")
    private BigDecimal loanAmount;

    @NotNull
    @Positive
    @Schema(description = "Loan term in years", example = "30")
    private Integer loanTerm;

    @NotNull
    @PositiveOrZero
    @Schema(description = "Interest rate margin", example = "1.0")
    private BigDecimal interestRateMargin;

    @NotNull
    @PositiveOrZero
    @Schema(description = "Euribor term in months", example = "3")
    private Integer euriborTerm;

    @NotNull
    @PositiveOrZero
    private BigDecimal interestRateEuribor;

    @NotNull
    @Schema(description = "Payment schedule type", example = "ANNUITY")
    private PaymentScheduleType paymentScheduleType;

    @Schema(description = "Co-applicant first name")
    private String coApplicantFirstName;

    @Schema(description = "Co-applicant last name")
    private String coApplicantLastName;

    @Email
    @Schema(description = "Co-applicant email")
    private String coApplicantEmail;

    @Schema(description = "Co-applicant phone number")
    private String coApplicantPhoneNumber;
}