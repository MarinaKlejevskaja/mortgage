package com.academy.mortgage.services;

import com.academy.mortgage.model.Applications;
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

    public Applications addApplication(Applications application) {
        return applicationsRepository.save(application);
    }
}
