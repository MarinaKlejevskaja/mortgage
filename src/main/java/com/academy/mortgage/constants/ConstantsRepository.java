package com.academy.mortgage.constants;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConstantsRepository extends JpaRepository<Constants, Long> {
    List<Constants> findAll();
}
