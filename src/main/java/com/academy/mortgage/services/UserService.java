package com.academy.mortgage.services;

import com.academy.mortgage.exceptions.DuplicateUserException;
import com.academy.mortgage.model.User;
import com.academy.mortgage.model.api.request.ApplicationRequest;
import com.academy.mortgage.model.enums.Role;
import com.academy.mortgage.repositories.UserRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.postgresql.util.PSQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User addUser(ApplicationRequest applicationRequest) {
        String password = RandomStringUtils.randomAlphanumeric(10);
        User user = User.builder()
                .firstName(applicationRequest.getFirstName())
                .lastName(applicationRequest.getLastName())
                .email(applicationRequest.getEmail())
                .password(passwordEncoder.encode(password))
                .role(Role.USER)
                .build();

        try {
            User savedUser = userRepository.save(user);
            sendTempPasswordByEmail(applicationRequest.getEmail(), password);

            return savedUser;
        } catch (DataIntegrityViolationException e) {
            if (e.getRootCause() instanceof PSQLException && ((PSQLException) e.getRootCause()).getSQLState().equals("23505")) {
                throw new DuplicateUserException("A user with this email already exists.");
            } else {
                throw e;
            }
        }
    }

    private void sendTempPasswordByEmail(String toEmail, String tempPassword) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreply@shrimp-eating-bankers.com");
        message.setTo(toEmail);
        message.setSubject("Temporary Password");
        message.setText("Your temporary password is: " + tempPassword);
        javaMailSender.send(message);
    }
}
