package com.academy.mortgage.controllers;

import com.academy.mortgage.exceptions.UserNotFoundException;
import com.academy.mortgage.model.Applications;
import com.academy.mortgage.model.User;
import com.academy.mortgage.model.api.response.EmailAvailabilityResponse;
import com.academy.mortgage.model.enums.Role;
import com.academy.mortgage.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("users/check-email")
    public ResponseEntity<EmailAvailabilityResponse> checkEmailAvailability(@RequestParam String email) {
        boolean exists = userService.checkEmail(email);
        if (exists) {
            EmailAvailabilityResponse response = new EmailAvailabilityResponse(false, "Looks like you already have submitted application. Please log it to check your application status");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        } else {
            EmailAvailabilityResponse response = new EmailAvailabilityResponse(true, "Email is available");
            return ResponseEntity.ok(response);
        }
}

    @GetMapping("users/get-role")
    public Role getAllUsers(@RequestParam String email) {
        return userService.findByEmail(email).getRole();
    }
    @GetMapping("/users/{userId}/applications")
    public List<Applications> getApplicationForUser(@PathVariable Long userId) {
        User user = userService.getUserById(userId);
        if (user == null) {
            throw new UserNotFoundException(userId);
        }
        return user.getApplications();
    }

}


