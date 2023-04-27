package com.academy.mortgage.services;

import com.academy.mortgage.exceptions.ConstantsNotFoundException;
import com.academy.mortgage.model.Constants;
import com.academy.mortgage.model.api.request.ConstantsRequest;
import com.academy.mortgage.repositories.ConstantsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ConstantsService {

    @Autowired
    ConstantsRepository constantsRepository;

    public Constants getConstants() {
        return constantsRepository.findById(1).orElseGet(() -> {
            constantsRepository.save(fallbackConstants());
            return constantsRepository.findById(1).orElseThrow(() -> new ConstantsNotFoundException(1L));
        });
    }

    public Constants fallbackConstants() {
        Constants fallBackConstants = new Constants();
        fallBackConstants.setId(1);
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

    public Constants updateConstants(ConstantsRequest request) {
        Constants constants;
        Optional<Constants> optionalConstants = constantsRepository.findById(1);
        if (optionalConstants.isPresent()) {
            constants = optionalConstants.get();
        } else {
            constants = fallbackConstants();
        }

        if (request.getMinLoanTerm() != null) {
            constants.setMinLoanTerm(request.getMinLoanTerm());
        }
        if (request.getMaxLoanTerm() != null) {
            constants.setMaxLoanTerm(request.getMaxLoanTerm());
        }
        if (request.getMaxNumOfApplicants() != null) {
            constants.setMaxNumOfApplicants(request.getMaxNumOfApplicants());
        }
        if (request.getLoanAmountPercentage() != null) {
            constants.setLoanAmountPercentage(request.getLoanAmountPercentage());
        }
        if (request.getInterestRateMargin() != null) {
            constants.setInterestRateMargin(request.getInterestRateMargin());
        }
        if (request.getMaxKids() != null) {
            constants.setMaxKids(request.getMaxKids());
        }
        if (request.getMinKids() != null) {
            constants.setMinKids(request.getMinKids());
        }
        if (request.getMaxMonthlyObligationsPercentage() != null) {
            constants.setMaxMonthlyObligationsPercentage(request.getMaxMonthlyObligationsPercentage());
        }
        constants.setId(1);
        return constantsRepository.save(constants);
    }
}
