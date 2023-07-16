package com.codersfactory.user;

import com.codersfactory.user.dto.UserRegisterDto;
import com.codersfactory.user.exception.UserNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    final UserRepository repository;
    final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository repository, BCryptPasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public User findByEmail(String email) {
        return repository.findUserByEmail(email).orElseThrow(
                UserNotFoundException::new
        );
    }

    public void registerUser(UserRegisterDto dto) {
        repository.save(createUser(dto));
    }

    User createUser(UserRegisterDto dto) {
        User user = new User(dto);
        user.setPassword(passwordEncoder.encode(dto.password()));
        return user;
    }
}
