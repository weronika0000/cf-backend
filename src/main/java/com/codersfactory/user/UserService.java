package com.codersfactory.user;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User findByEmail(String email) {
        return repository.findUserByEmail(email).orElseThrow();
    }
}
