package com.academy.mortgage.services;

import com.academy.mortgage.controllers.AuthenticationRequest;
import com.academy.mortgage.controllers.AuthenticationResponse;
import com.academy.mortgage.controllers.RegisterRequest;
import com.academy.mortgage.model.User;
import com.academy.mortgage.model.enums.Role;
import com.academy.mortgage.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword()
        )
);
// todo implement exception
var user = repository.findByEmail(request.getEmail())
        .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

}
