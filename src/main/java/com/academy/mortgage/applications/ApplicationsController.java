package com.academy.mortgage.applications;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApplicationsController {
    @Autowired
    ApplicationsRepository applicationsRepository;
}
