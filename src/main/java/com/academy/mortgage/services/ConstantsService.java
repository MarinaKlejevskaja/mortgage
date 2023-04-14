package com.academy.mortgage.services;

import com.academy.mortgage.model.Constants;
import com.academy.mortgage.repositories.ConstantsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ConstantsService {

    @Autowired
    ConstantsRepository constantsRepository;

    public Constants getConstants() {
        return constantsRepository.findById(1L).orElse(null);
    }
}
