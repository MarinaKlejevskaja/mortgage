package com.academy.mortgage.services;

import com.academy.mortgage.exceptions.ApplicationNotFoundException;
import com.academy.mortgage.exceptions.DuplicateUserException;
import com.academy.mortgage.exceptions.UserNotFoundException;
import com.academy.mortgage.model.Applications;
import com.academy.mortgage.model.User;
import com.academy.mortgage.model.api.request.ApplicationRequest;
import com.academy.mortgage.model.api.request.ApplicationStatusUpdateRequest;
import com.academy.mortgage.model.api.response.ApplicationsResponse;
import com.academy.mortgage.model.api.response.UsersApplicationResponse;
import com.academy.mortgage.model.enums.ApplicationStatus;
import com.academy.mortgage.repositories.ApplicationsRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
                    .applicationId(application.getApplicationId())
                    .applicants(application.getApplicantsAmount())
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

        User user = null;
        String password = null;
        boolean newUser = false;

        try{
            user = userService.getUserByEmail(applicationRequest.getEmail());
            user.setFirstName(applicationRequest.getFirstName());
            user.setLastName(applicationRequest.getLastName());
            user.setPhoneNumber(applicationRequest.getPhoneNumber());
            user.setPersonalNumber(applicationRequest.getPersonalNumber());
            userService.updateUser(user);

        } catch (UserNotFoundException e) {
            password = RandomStringUtils.randomAlphanumeric(10);
            user = userService.addUser(applicationRequest, password);
            newUser = true;
        }

        Applications application = Applications.builder()
                .userId(user.getId())
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
            if (newUser) {
                sendWelcomeEmail(user.getEmail(), password);
            }
            sendApplicationSubmittedEmail(user.getEmail());
            return applications;
        } catch (Exception e) {
            if (newUser) {
                userService.deleteUser(user.getId());
            }
            throw e;
        }
    }

    private void sendWelcomeEmail(String toEmail, String tempPassword) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreply@shrimp-eating-bankers.com");
        message.setTo(toEmail);
        message.setSubject("Welcome to Shrimp Eating Bankers");
        message.setText("Dear customer,\n\nThank you for creating an account with Shrimp Eating Bankers. Your temporary password is: " + tempPassword + "\n\nPlease use this password to login to your account and set up a new, secure password.\n\nBest regards,\nThe Shrimp Eating Bankers Team");
        javaMailSender.send(message);
    }

    private void sendApplicationSubmittedEmail(String toEmail) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreply@shrimp-eating-bankers.com");
        message.setTo(toEmail);
        message.setSubject("Your Loan Application Has Been Submitted");
        message.setText("Dear customer,\n\nThank you for submitting a loan application with Shrimp Eating Bankers. Our team is currently reviewing your application and will be in touch with you shortly.\n\nBest regards,\nThe Shrimp Eating Bankers Team");
        javaMailSender.send(message);
    }

    public List<UsersApplicationResponse> getApplicationsByUserEmail(String email) {
        Long userId = userService.getUserByEmail(email).getId();
        if (userId == null) {
            throw new UserNotFoundException(email);
        }
        List<Applications> applications = applicationsRepository.findAllByUserId(userId);
        List<UsersApplicationResponse> responseList = new ArrayList<>();
        for (Applications application : applications) {
            UsersApplicationResponse response = UsersApplicationResponse.builder()
                    .applicationId(application.getApplicationId())
                    .realEstateAddress(application.getRealEstateAddress())
                    .realEstatePrice(application.getRealEstatePrice())
                    .downPayment(application.getDownPayment())
                    .loanAmount(application.getLoanAmount())
                    .loanTerm(application.getLoanTerm())
                    .paymentScheduleType(application.getPaymentScheduleType())
                    .euriborTerm(application.getEuriborTerm())
                    .interestRateEuribor(application.getInterestRateEuribor())
                    .applicationStatus(application.getApplicationStatus())
                    .build();

            responseList.add(response);
        }
        return responseList;
    }

    public void updateApplicationStatus(ApplicationStatusUpdateRequest applicationStatusUpdateRequest) {
        Long applicationId = applicationStatusUpdateRequest.getApplicationId();
        Applications application = applicationsRepository.findByApplicationId(applicationStatusUpdateRequest.getApplicationId());
        if(application == null) {
            throw new ApplicationNotFoundException(applicationId);
        }
        application.setApplicationStatus(applicationStatusUpdateRequest.getApplicationStatus());
        applicationsRepository.save(application);
    }

    public Boolean checkUserHasApplication(Long id) {
        Applications application = applicationsRepository.findByUserId(id);
        if(application == null) {
           return false;
        }
        return true;
    }
}

