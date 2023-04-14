package com.academy.mortgage.constants;

import com.academy.mortgage.constants.enums.ApplicationStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class ConstantsService {

    @Autowired
    ConstantsRepository constantsRepository;

    public Constants getConstants() {
        Constants constants = constantsRepository.findById(1L).orElse(null);
        if (constants == null) {
            constants = new Constants();
            constants.setId(1L);
            constants.setMinLoanTerm(1);
            constants.setMaxLoanTerm(30);
            constants.setMaxNumOfApplicants(2);
            constants.setLoanAmountPercentage(0.85f);
            constants.setInterestRateMargin(0.025f);
            constants.setMinKids(0);
            constants.setMaxKids(10);
            constants.setMaxMonthlyObligationsPercentage(0.4f);
            constants.setApplicationStatus(ApplicationStatus.RECEIVED);
            constants.setMinKids(0);
            constantsRepository.save(constants);
        }
        return constants;
    }
}
