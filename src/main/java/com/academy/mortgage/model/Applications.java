package com.academy.mortgage.model;

import com.academy.mortgage.model.enums.ApplicationStatus;
import com.academy.mortgage.model.enums.PaymentScheduleType;
import com.academy.mortgage.serializers.UsersSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
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
    private Long id;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonSerialize(using = UsersSerializer.class)
    private Users userId;
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
