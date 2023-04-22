package com.academy.mortgage.services;

import com.academy.mortgage.exceptions.ConstantsNotFoundException;
import com.academy.mortgage.model.Constants;
import com.academy.mortgage.repositories.ConstantsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConstantsService {

    @Autowired
    ConstantsRepository constantsRepository;

    public Constants getConstants() {
        return constantsRepository.findById(1L).orElseGet(() -> {
            constantsRepository.save(fallbackConstants());
            return constantsRepository.findById(1L).orElseThrow(() -> new ConstantsNotFoundException(1L));
        });
    }

    public Constants fallbackConstants() {
        Constants fallBackConstants = new Constants();
        fallBackConstants.setId(1L);
        fallBackConstants.setMinLoanTerm(1);
        fallBackConstants.setMaxLoanTerm(30);
        fallBackConstants.setMaxNumOfApplicants(2);
        fallBackConstants.setLoanAmountPercentage(0.85f);
        fallBackConstants.setInterestRateMargin(0.025f);
        fallBackConstants.setMinKids(0);
        fallBackConstants.setMaxKids(10);
        fallBackConstants.setMaxMonthlyObligationsPercentage(0.4f);
        return fallBackConstants;
    }

    public Constants updateConstants(Constants updatedConstants) {
        return constantsRepository.findById(1L)
                .map(constants -> {
                    constants.setMinLoanTerm(updatedConstants.getMinLoanTerm());
                    constants.setMaxLoanTerm(updatedConstants.getMaxLoanTerm());
                    constants.setMaxNumOfApplicants(updatedConstants.getMaxNumOfApplicants());
                    constants.setLoanAmountPercentage(updatedConstants.getLoanAmountPercentage());
                    constants.setInterestRateMargin(updatedConstants.getInterestRateMargin());
                    constants.setMaxKids(updatedConstants.getMaxKids());
                    constants.setMinKids(updatedConstants.getMinKids());
                    constants.setMaxMonthlyObligationsPercentage(updatedConstants.getMaxMonthlyObligationsPercentage());
                    return constantsRepository.save(constants);
                })
                .orElseGet(() -> {
                    updatedConstants.setId(1L); // Set the ID to 1 since you expect only one Constants entry
                    return constantsRepository.save(updatedConstants);
                });
    }
}
