package com.academy.mortgage.controllers;

import com.academy.mortgage.model.Constants;
import com.academy.mortgage.model.api.request.ConstantsRequest;
import com.academy.mortgage.services.ConstantsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class ConstantsController {
    @Autowired
    ConstantsService constantsService;

    @GetMapping("/constants")
    public ResponseEntity<Constants> getConstants() {
        Constants constants = constantsService.getConstants();
        return new ResponseEntity<>(constants, HttpStatus.OK);
    }

    @PutMapping("/auth/constants")
    public ResponseEntity<Constants> updateConstants(@RequestBody ConstantsRequest updatedConstants) {
        Constants constants = constantsService.updateConstants(updatedConstants);
        return new ResponseEntity<>(constants, HttpStatus.OK);
    }
}
