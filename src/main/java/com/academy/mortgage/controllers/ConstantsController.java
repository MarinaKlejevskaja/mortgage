package com.academy.mortgage.controllers;

import com.academy.mortgage.model.Constants;
import com.academy.mortgage.services.ConstantsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

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
