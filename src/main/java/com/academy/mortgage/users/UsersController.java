package com.academy.mortgage.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
class UsersController {
    @Autowired
    UsersRepository usersRepository;
}
