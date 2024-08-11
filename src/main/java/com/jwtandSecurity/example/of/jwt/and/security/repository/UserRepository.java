package com.jwtandSecurity.example.of.jwt.and.security.repository;

import com.jwtandSecurity.example.of.jwt.and.security.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
