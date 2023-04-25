package com.academy.mortgage.model.api.request;

import com.academy.mortgage.model.enums.PaymentScheduleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationData{
        String address;
        Integer amountOfKids;
        Integer applicants;
        Float coApplicantsIncome;
        Float consumerLoans;
        Float creditCardLimit;
        Float downPayment;
        String email;
        Float euribor;
        String firstName;
        Float income;
        String lastName;
        Float leasingAmount;
        Float loanAmount;
        Integer loanTerm;
        Float monthlyPayment;
        Float mortgageLoans;
        Float obligations;
        PaymentScheduleType paymentScheduleType;
        String personalNumber;
        String phoneNumber;
        String realEstateAddress;
        Float realEstatePrice;
        Float interestRateMargin;
}