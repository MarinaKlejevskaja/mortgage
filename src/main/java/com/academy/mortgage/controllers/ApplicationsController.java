package com.academy.mortgage.controllers;

import com.academy.mortgage.model.Applications;
import com.academy.mortgage.model.api.request.ApplicationRequest;
import com.academy.mortgage.services.ApplicationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class ApplicationsController {
    @Autowired
    ApplicationsService applicationsService;

    @GetMapping("auth/applications")
    public List<Applications> all() {
        return applicationsService.getApplications();
    }

    @PostMapping("new-application")
    public void save(@RequestBody ApplicationRequest applicationRequest) {
        applicationsService.addApplication(applicationRequest);
    }
}
