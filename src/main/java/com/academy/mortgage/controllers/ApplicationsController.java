package com.academy.mortgage.controllers;

import com.academy.mortgage.repositories.ApplicationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApplicationsController {
    @Autowired
    ApplicationsRepository applicationsRepository;
}
