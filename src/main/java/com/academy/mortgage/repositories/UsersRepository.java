package com.academy.mortgage.repositories;

import com.academy.mortgage.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsersRepository extends JpaRepository<Users, Long> {
    List<Users> findAll();
}
