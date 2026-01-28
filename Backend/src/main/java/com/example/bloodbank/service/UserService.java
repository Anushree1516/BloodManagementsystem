package com.example.bloodbank.service;

import com.example.bloodbank.model.User;
import com.example.bloodbank.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User register(User user) {
        return repository.save(user);
    }

    public User getUserById(Long id) {
        return repository.findById(id).orElse(null);
    }


    public User login(String email, String password) {
        return repository
                .findByEmailAndPassword(email, password)
                .orElse(null);
    }
}

