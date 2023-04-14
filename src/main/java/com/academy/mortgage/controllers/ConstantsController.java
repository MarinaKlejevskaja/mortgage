package com.academy.mortgage.controllers;

import com.academy.mortgage.exceptions.ConstantsNotFoundException;
import com.academy.mortgage.model.Constants;
import com.academy.mortgage.services.ConstantsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ConstantsController {
    @Autowired
    ConstantsService constantsService;

    @GetMapping("/constants")
    public ResponseEntity<Constants> getConstants() {
        Constants constants = constantsService.getConstants();
        return new ResponseEntity<>(constants, HttpStatus.OK);
    }

    @PutMapping("/constants")
    public ResponseEntity<Constants> updateConstants(@RequestBody Constants updatedConstants) {
        Constants constants = constantsService.updateConstants(updatedConstants);
        return new ResponseEntity<>(constants, HttpStatus.OK);
    }

    @ExceptionHandler(ConstantsNotFoundException.class)
    public ResponseEntity<String> handleConstantsNotFoundException(ConstantsNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
