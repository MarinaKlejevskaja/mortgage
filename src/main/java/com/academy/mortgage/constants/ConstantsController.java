package com.academy.mortgage.constants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConstantsController {
    @Autowired
    ConstantsService constantsService;

    @GetMapping("/api/constants")
    public Constants getConstants() {
        return constantsService.getConstants();
    }
}
