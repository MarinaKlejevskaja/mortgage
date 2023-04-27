package com.academy.mortgage.controllers;

import com.academy.mortgage.model.Applications;
import com.academy.mortgage.model.api.request.ApplicationRequest;
import com.academy.mortgage.model.api.response.ApplicationsResponse;
import com.academy.mortgage.services.ApplicationsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ApplicationsController {
    @Autowired
    ApplicationsService applicationsService;

    @GetMapping("/applications")
    public List<ApplicationsResponse> all() {
        return applicationsService.getApplications();
    }

    @PostMapping("/new-application")
    public ResponseEntity<Applications> save(@Valid @RequestBody ApplicationRequest applicationRequest) {
        applicationsService.addApplication(applicationRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
