package com.academy.mortgage.constants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "https://be-mortgage-calculator.onrender.com")
@RestController
@RequestMapping("/api")
public class ConstantsController {
    @Autowired
    ConstantsService constantsService;

    @GetMapping("/constants")
    public Constants getConstants() {
        return constantsService.getConstants();
    }
}
