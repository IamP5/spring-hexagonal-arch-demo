package com.example.springhexagonaldemo.infrastructure.db.repository;

import com.example.springhexagonaldemo.infrastructure.db.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<CustomerEntity, String> {
    Optional<CustomerEntity> findByEmail(String email);
}
