package com.academy.mortgage.controllers;

import com.academy.mortgage.exceptions.UserNotFoundException;
import com.academy.mortgage.model.Applications;
import com.academy.mortgage.model.User;
import com.academy.mortgage.model.api.response.EmailAvailabilityResponse;
import com.academy.mortgage.model.api.response.UserResponse;
import com.academy.mortgage.model.enums.Role;
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

    @GetMapping("/users/check-email")
    public ResponseEntity<EmailAvailabilityResponse> checkEmailAvailability(@RequestParam String email) {
        ResponseEntity<EmailAvailabilityResponse> response = userService.checkEmail(email);
        return response;
    }


    @GetMapping("/users/get-role")
    public Role getAllUsers(@RequestParam String email) {
        return userService.findByEmail(email).getRole();
    }

    @GetMapping("/user/{userId}/application")
        public List<Applications> getApplicationForEmail(@PathVariable Long userId) {
        User user = userService.getUserById(userId);
        if (user == null) {
            throw new UserNotFoundException(userId);
        }
        return user.getApplications();
    }

    @GetMapping("/user/{userEmail}/application")
    public List<Applications> getApplicationForEmail(@PathVariable String userEmail) {
        User user = userService.findByEmail(userEmail);
        if (user == null) {
            throw new UserNotFoundException(userEmail);
        }
        return user.getApplications();
}

    @GetMapping("/auth/users")
    public UserResponse getUserInfo(@RequestParam String email) {
        return userService.getUserInfo(email);
    }

}


