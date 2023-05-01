package com.academy.mortgage.controllers;

import com.academy.mortgage.model.Applications;
import com.academy.mortgage.model.api.request.ApplicationRequest;
import com.academy.mortgage.model.api.request.ApplicationStatusUpdateRequest;
import com.academy.mortgage.model.api.response.ApplicationsResponse;
import com.academy.mortgage.model.api.response.UsersApplicationResponse;
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

    @GetMapping("/admin/applications")
    public List<ApplicationsResponse> all() {
        return applicationsService.getApplications();
    }

    @GetMapping("/auth/applications")
    public List<ApplicationsResponse> all(@RequestParam String email) {
        return applicationsService.getApplicationsByUserEmail(email);
    }

    @PostMapping("/new-application")
    public ResponseEntity<Applications> save(@Valid @RequestBody ApplicationRequest applicationRequest) {
        applicationsService.addApplication(applicationRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/admin/update-application-status")
    public ResponseEntity<String> save(@Valid @RequestBody ApplicationStatusUpdateRequest applicationStatusUpdateRequest) {
        applicationsService.updateApplicationStatus(applicationStatusUpdateRequest);
        return new ResponseEntity<>("Application status updated", HttpStatus.OK);
    }
}
