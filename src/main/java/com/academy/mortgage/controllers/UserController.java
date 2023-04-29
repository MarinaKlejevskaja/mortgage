package com.academy.mortgage.controllers;

import com.academy.mortgage.model.api.response.EmailAvailabilityResponse;
import com.academy.mortgage.model.api.response.UserResponse;
import com.academy.mortgage.model.api.response.UsersApplicationResponse;
import com.academy.mortgage.model.enums.Role;
import com.academy.mortgage.services.ApplicationsService;
import com.academy.mortgage.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    ApplicationsService applicationsService;

    @GetMapping("/users/check-email")
    public ResponseEntity<EmailAvailabilityResponse> checkEmailAvailability(@RequestParam String email) {
        ResponseEntity<EmailAvailabilityResponse> response = userService.checkEmail(email);
        return response;
    }

    @GetMapping("/users/get-role")
    public Role getAllUsers(@RequestParam String email) {
        return userService.findByEmail(email).getRole();
    }

    @GetMapping("/user/id/{userId}/applications")
    public List<UsersApplicationResponse> getAllApplicationsByUserId(@PathVariable Long userId) {
        return applicationsService.getApplicationsByUserId(userId);
    }

    @GetMapping("/user/email/{userEmail}/applications")
    public List<UsersApplicationResponse> getAllApplicationsByUserEmail(@PathVariable String userEmail) {
        return applicationsService.getApplicationsByUserEmail(userEmail);
    }

    @GetMapping("/auth/users")
    public UserResponse getUserInfo(@RequestParam String email) {
        return userService.getUserInfo(email);
    }
}


