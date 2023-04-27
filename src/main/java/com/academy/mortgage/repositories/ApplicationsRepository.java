package com.academy.mortgage.repositories;

import com.academy.mortgage.model.Applications;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationsRepository extends JpaRepository<Applications, Long> {
    List<Applications> findAll();
    List<Applications> findAllByUserId(Long userId);
}
