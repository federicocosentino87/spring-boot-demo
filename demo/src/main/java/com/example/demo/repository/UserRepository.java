package com.example.demo.repository;

import java.util.Optional;

import com.example.demo.domain.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
    Optional<User> findByEmailAndActivationCode(String email, String activationCode);
}
