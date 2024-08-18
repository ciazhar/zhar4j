package com.ciazhar.cleanarchitecture.service;

import com.ciazhar.cleanarchitecture.model.User;
import com.ciazhar.cleanarchitecture.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    // Add a new user
    public void addUser(User user) {
        userRepository.addUser(user);
    }

    // Retrieve a user by username
    public Optional<User> getUserByUsername(String username) {
        return userRepository.getUserByUsername(username);
    }

    // Retrieve all users
    public Map<String, User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    // Delete a user by username
    public boolean deleteUser(String username) {
        return userRepository.deleteUser(username);
    }

    // Update an existing user
    public void updateUser(User user) {
        userRepository.updateUser(user);
    }
}