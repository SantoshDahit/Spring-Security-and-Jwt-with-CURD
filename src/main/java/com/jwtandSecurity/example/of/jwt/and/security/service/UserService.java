package com.jwtandSecurity.example.of.jwt.and.security.service;

import com.jwtandSecurity.example.of.jwt.and.security.entities.User;
import com.jwtandSecurity.example.of.jwt.and.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User updateUser(Long id, User updateUser) {
        Optional<User> existingUser = userRepository.findById(id);
        if (existingUser.isPresent()) {
            User updatingExistingUser = existingUser.get();
            updatingExistingUser.setName(updateUser.getName());
            updatingExistingUser.setEmail(updateUser.getEmail());

            if (updateUser.getPassword() != null && !updateUser.getPassword().isEmpty()) {
                updatingExistingUser.setPassword(passwordEncoder.encode(updateUser.getPassword()));
            }

            return userRepository.save(updatingExistingUser);
        } else {
            throw new RuntimeException("User not found with id: " + id);
        }
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
