package com.academy.mortgage.services;

import com.academy.mortgage.exceptions.DuplicateUserException;
import com.academy.mortgage.model.Applications;
import com.academy.mortgage.model.User;
import com.academy.mortgage.model.api.request.ApplicationRequest;
import com.academy.mortgage.model.api.response.ApplicationsResponse;
import com.academy.mortgage.model.enums.ApplicationStatus;
import com.academy.mortgage.repositories.ApplicationsRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ApplicationsService {
    @Autowired
    ApplicationsRepository applicationsRepository;
    @Autowired
    UserService userService;

    @Autowired
    private JavaMailSender javaMailSender;

    public List<ApplicationsResponse> getApplications() {
        List<Applications> applications = applicationsRepository.findAll();
        List<ApplicationsResponse> responseList = new ArrayList<>();

        for (Applications application : applications) {
            User user = userService.getUserById(application.getUserId());
            ApplicationsResponse response = ApplicationsResponse.builder()
                    .amountOfKids(application.getApplicantsAmount())
                    .monthlyIncome(application.getMonthlyIncome())
                    .coApplicantsIncome(application.getCoApplicantsIncome())
                    .obligations(application.getObligations())
                    .mortgageLoans(application.getMortgageLoans())
                    .consumerLoans(application.getConsumerLoans())
                    .leasingAmount(application.getLeasingAmount())
                    .creditCardLimit(application.getCreditCardLimit())
                    .monthlyPayment(application.getAvailableMonthlyPayment())
                    .realEstateAddress(application.getRealEstateAddress())
                    .realEstatePrice(application.getRealEstatePrice())
                    .downPayment(application.getDownPayment())
                    .loanAmount(application.getLoanAmount())
                    .loanTerm(application.getLoanTerm())
                    .paymentScheduleType(application.getPaymentScheduleType())
                    .interestRateMargin(application.getInterestRateMargin())
                    .euriborTerm(application.getEuriborTerm())
                    .interestRateEuribor(application.getInterestRateEuribor())
                    .totalHouseholdIncome(application.getTotalHouseholdIncome())
                    .coApplicantEmail(application.getCoApplicantEmail())
                    .applicationStatus(application.getApplicationStatus())
                    .firstName(user.getFirstName())
                    .lastName(user.getLastName())
                    .personalNumber(user.getPersonalNumber())
                    .email(user.getEmail())
                    .phoneNumber(user.getPhoneNumber())
                    .address(user.getAddress())
                    .build();

            responseList.add(response);
        }

        return responseList;
    }

    public Applications addApplication(ApplicationRequest applicationRequest) {
        Long userId;
        String password = RandomStringUtils.randomAlphanumeric(10);
        try {
            userId = userService.addUser(applicationRequest,password).getId();
        } catch (DuplicateUserException e) {
            throw e;
        }

        Applications application = Applications.builder()
                .userId(userId)
                .monthlyIncome(applicationRequest.getMonthlyIncome())
                .coApplicantsIncome(applicationRequest.getCoApplicantsIncome())
                .totalHouseholdIncome(applicationRequest.getTotalHouseholdIncome())
                .obligations(applicationRequest.getObligations())
                .mortgageLoans(applicationRequest.getMortgageLoans())
                .consumerLoans(applicationRequest.getConsumerLoans())
                .leasingAmount(applicationRequest.getLeasingAmount())
                .creditCardLimit(applicationRequest.getCreditCardLimit())
                .availableMonthlyPayment(applicationRequest.getMonthlyPayment())
                .realEstateAddress(applicationRequest.getRealEstateAddress())
                .realEstatePrice(applicationRequest.getRealEstatePrice())
                .downPayment(applicationRequest.getDownPayment())
                .loanAmount(applicationRequest.getLoanAmount())
                .loanTerm(applicationRequest.getLoanTerm())
                .interestRateMargin(applicationRequest.getInterestRateMargin())
                .interestRateEuribor(applicationRequest.getInterestRateEuribor())
                .euriborTerm(applicationRequest.getEuriborTerm())
                .paymentScheduleType(applicationRequest.getPaymentScheduleType())
                .amountOfKids(applicationRequest.getAmountOfKids())
                .applicantsAmount(applicationRequest.getApplicants())
                .coApplicantEmail(applicationRequest.getCoApplicantEmail())
                .applicationStatus(ApplicationStatus.RECEIVED)
                .build();

        try {
            Applications applications = applicationsRepository.save(application);
            sendTempPasswordByEmail(applicationRequest.getEmail(), password);
            return applications;
        }catch (Exception e){
            throw e;
        }
    }

    private void sendTempPasswordByEmail(String toEmail, String tempPassword) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreply@shrimp-eating-bankers.com");
        message.setTo(toEmail);
        message.setSubject("Temporary Password");
        message.setText("Your temporary password is: " + tempPassword);
        javaMailSender.send(message);
    }
}
