package com.academy.mortgage.services;

import com.academy.mortgage.model.Applications;
import com.academy.mortgage.model.api.request.ApplicationRequest;
import com.academy.mortgage.model.enums.ApplicationStatus;
import com.academy.mortgage.repositories.ApplicationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationsService {
    @Autowired
    ApplicationsRepository applicationsRepository;
    @Autowired
    UserService userService;

    public List<Applications> getApplications() {
        return applicationsRepository.findAll();
    }

    public Applications addApplication(ApplicationRequest applicationRequest) {
        Long userId;
        try {
            userId = userService.addUser(applicationRequest).getId();
        } catch (Exception e) {
            throw e;
        }
        Applications application = Applications.builder()
                .userId(userId)
                .monthlyIncome(applicationRequest.getMonthlyIncome())
                .obligations(applicationRequest.getObligations())
                .mortgageLoans(applicationRequest.getMortgageLoans())
                .consumerLoans(applicationRequest.getConsumerLoans())
                .leasingAmount(applicationRequest.getLeasingAmount())
                .creditCardLimit(applicationRequest.getCreditCardLimit())
                .realEstatePrice(applicationRequest.getRealEstatePrice())
                .downPayment(applicationRequest.getDownPayment())
                .loanAmount(applicationRequest.getLoanAmount())
                .loanTerm(applicationRequest.getLoanTerm())
                .interestRateMargin(applicationRequest.getInterestRateMargin())
                .interestRateEuribor(applicationRequest.getInterestRateEuribor())
                .euriborTerm(applicationRequest.getEuriborTerm())
                .paymentScheduleType(applicationRequest.getPaymentScheduleType())
                .childrenAmount(applicationRequest.getAmountOfKids())
                .applicantsAmount(applicationRequest.getApplicants())
                .applicationStatus(ApplicationStatus.RECEIVED)
                .build();
        return applicationsRepository.save(application);
    }
}
