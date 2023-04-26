package com.academy.mortgage.controllers;

import com.academy.mortgage.exceptions.ConstantsNotFoundException;
import com.academy.mortgage.model.Constants;
import com.academy.mortgage.services.ConstantsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/constants")
public class ConstantsController {
    @Autowired
    ConstantsService constantsService;

    @GetMapping()
    public ResponseEntity<Constants> getConstants() {
        Constants constants = constantsService.getConstants();
        return new ResponseEntity<>(constants, HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<Constants> updateConstants(@RequestBody Constants updatedConstants) {
        Constants constants = constantsService.updateConstants(updatedConstants);
        return new ResponseEntity<>(constants, HttpStatus.OK);
    }
}
