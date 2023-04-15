package com.academy.mortgage.controllers;

import com.academy.mortgage.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsersController {
    @Autowired
    UsersRepository usersRepository;
}
