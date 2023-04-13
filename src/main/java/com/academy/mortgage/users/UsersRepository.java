package com.academy.mortgage.users;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsersRepository extends JpaRepository<Users, Long> {
    List<Users> findAll();
}
