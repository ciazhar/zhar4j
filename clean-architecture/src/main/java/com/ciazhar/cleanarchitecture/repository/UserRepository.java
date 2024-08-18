package com.ciazhar.cleanarchitecture.repository;

import com.ciazhar.cleanarchitecture.model.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class UserRepository {

    private final Map<String, User> users = new HashMap<>();

    // Add a new user to the repository
    public void addUser(User user) {
        users.put(user.getUsername(), user);
    }

    // Retrieve a user by their username
    public Optional<User> getUserByUsername(String username) {
        return Optional.ofNullable(users.get(username));
    }

    // Retrieve all users from the repository
    public Map<String, User> getAllUsers() {
        return new HashMap<>(users);
    }

    // Delete a user from the repository
    public boolean deleteUser(String username) {
        if (users.containsKey(username)) {
            users.remove(username);
            return true;
        }
        return false;
    }

    // Update an existing user in the repository
    public void updateUser(User user) {
        users.put(user.getUsername(), user);
    }
}