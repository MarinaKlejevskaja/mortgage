package com.academy.mortgage.services;

import com.academy.mortgage.exceptions.DuplicateUserException;
import com.academy.mortgage.exceptions.UserNotFoundException;
import com.academy.mortgage.model.Applications;
import com.academy.mortgage.model.User;
import com.academy.mortgage.model.api.request.ApplicationRequest;
import com.academy.mortgage.model.api.response.EmailAvailabilityResponse;
import com.academy.mortgage.model.api.response.UserResponse;
import com.academy.mortgage.model.enums.Role;
import com.academy.mortgage.repositories.ApplicationsRepository;
import com.academy.mortgage.repositories.UserRepository;
import org.postgresql.util.PSQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ApplicationsRepository applicationsRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException(email));
    }

    public ResponseEntity<EmailAvailabilityResponse> checkEmail(String email) {
        User user = userRepository.findByEmail(email).orElse(null);
        if (user == null) {
            EmailAvailabilityResponse response = new EmailAvailabilityResponse(true, "Email is available");
            return ResponseEntity.ok(response);
        } else {
            Applications userHasApplication = applicationsRepository.findByUserId(user.getId());
            EmailAvailabilityResponse response;
            if(userHasApplication == null) {
                response = new EmailAvailabilityResponse(false, "Looks like you already have an account. Please sign in before proceeding");
            } else {
                response = new EmailAvailabilityResponse(false, "Looks like you already have submitted application. Please sign in to check your application status");
            }
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }
    }

    public User addUser(ApplicationRequest applicationRequest, String password) {
        User user = User.builder()
                .firstName(applicationRequest.getFirstName())
                .lastName(applicationRequest.getLastName())
                .email(applicationRequest.getEmail())
                .password(passwordEncoder.encode(password))
                .personalNumber(applicationRequest.getPersonalNumber())
                .phoneNumber(applicationRequest.getPhoneNumber())
                .address(applicationRequest.getAddress())
                .role(Role.USER)
                .build();

        try {
            User savedUser = userRepository.save(user);

            return savedUser;
        } catch (DataIntegrityViolationException e) {
            if (e.getRootCause() instanceof PSQLException && ((PSQLException) e.getRootCause()).getSQLState().equals("23505")) {
                throw new DuplicateUserException("A user with this email already exists.");
            } else {
                throw e;
            }
        }
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
    }


    public List<Applications> getUserApplications(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            return optionalUser.get().getApplications();
        } else {
            throw new UserNotFoundException("User with ID " + userId + " not found");
        }
    }    
        
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException(email));
    }
    

    public UserResponse getUserInfo(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException(email));
        return UserResponse.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .address(user.getAddress())
                .build();

    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public void updateUser(User user) {
        userRepository.save(user);
    }
}
