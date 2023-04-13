package com.academy.mortgage.constants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConstantsController {
    @Autowired
    ConstantsRepository constantsRepository;
}
