package com.academy.mortgage.controllers;

import com.academy.mortgage.model.enums.Role;
import com.academy.mortgage.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("users/check-email")
    public ResponseEntity<String> checkEmailAvailability(@RequestParam String email) {
        boolean exists = userService.checkEmail(email);
        if (exists) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Looks like you have already submitted an application. Please log in to check your application status.");
        } else {
            return ResponseEntity.ok("Email is available");
        }
    }

    @GetMapping("users/get-role")
    public Role getAllUsers(@RequestParam String email) {
        return userService.findByEmail(email).getRole();
    }
}


