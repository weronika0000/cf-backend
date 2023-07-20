package com.codersfactory.user;

import com.codersfactory.user.exception.UserNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    final UserService service;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new CustomUserDetails(service.findByUsername(username));
    }

    public UserDetails loadUserByEmail(String email) throws UserNotFoundException {
        return new CustomUserDetails(service.findByEmail(email));
    }
}
