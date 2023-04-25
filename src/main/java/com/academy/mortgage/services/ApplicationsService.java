package com.academy.mortgage.services;

import com.academy.mortgage.model.Applications;
import com.academy.mortgage.model.api.request.ApplicationRequest;
import com.academy.mortgage.repositories.ApplicationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationsService {
    @Autowired
    ApplicationsRepository applicationsRepository;

    public List<Applications> getApplications() {
        return applicationsRepository.findAll();
    }

    public Applications addApplication(ApplicationRequest applicationRequest) {
        Applications application = Applications.builder()
                .monthlyIncome(applicationRequest.getMonthlyIncome())
                .mortgageLoans(applicationRequest.getMortgageLoans())
                .consumerLoans(applicationRequest.getConsumerLoans())

                .build();
        return applicationsRepository.save(application);
    }
}
