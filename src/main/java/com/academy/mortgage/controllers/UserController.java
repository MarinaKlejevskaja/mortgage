package com.academy.mortgage.controllers;

import com.academy.mortgage.model.api.response.EmailAvailabilityResponse;
import com.academy.mortgage.model.api.response.UserResponse;
import com.academy.mortgage.model.enums.Role;
import com.academy.mortgage.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/users/check-email")
    public ResponseEntity<EmailAvailabilityResponse> checkEmailAvailability(@RequestParam String email) {

        ResponseEntity<EmailAvailabilityResponse> response = userService.checkEmail(email);
        return response;
    }

    @GetMapping("/users/get-role")
    public Role getAllUsers(@RequestParam String email) {
        return userService.findByEmail(email).getRole();
    }

    @GetMapping("/auth/users")
    public UserResponse getUserInfo(@RequestParam String email) {
        return userService.getUserInfo(email);
    }
}


