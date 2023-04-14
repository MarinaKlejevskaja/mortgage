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
//     @Autowired
//    ConstantsRepository constantsRepository;
//
//    public Constants getConstants() {
//        return constantsRepository.findById(1L).orElseThrow(() -> new ConstantsNotFoundException(1L));
//    }
//    public Constants updateConstants(Constants updatedConstants) {
//        return constantsRepository.findById(1L)
//                .map(constants -> {
//                    constants.setMinLoanTerm(updatedConstants.getMinLoanTerm());
//                    constants.setMaxLoanTerm(updatedConstants.getMaxLoanTerm());
//                    constants.setMaxNumOfApplicants(updatedConstants.getMaxNumOfApplicants());
//                    constants.setLoanAmountPercentage(updatedConstants.getLoanAmountPercentage());
//                    constants.setInterestRateMargin(updatedConstants.getInterestRateMargin());
//                    constants.setMaxKids(updatedConstants.getMaxKids());
//                    constants.setMinKids(updatedConstants.getMinKids());
//                    constants.setMaxMonthlyObligationsPercentage(updatedConstants.getMaxMonthlyObligationsPercentage());
//                    return constantsRepository.save(constants);
//                })
//                .orElseGet(() -> {
//                    updatedConstants.setId(1L); // Set the ID to 1 since you expect only one Constants entry
//                    return constantsRepository.save(updatedConstants);
//                });
//    }
}
