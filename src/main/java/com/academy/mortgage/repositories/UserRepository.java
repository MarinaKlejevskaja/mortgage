package com.academy.mortgage.repositories;

import com.academy.mortgage.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAll();

    Optional<User> findByEmail(String email);
}
