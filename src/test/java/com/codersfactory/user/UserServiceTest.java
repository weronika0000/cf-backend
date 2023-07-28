package com.codersfactory.user;

import com.codersfactory.user.dto.UserRegisterDto;
import com.codersfactory.user.exception.UserNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    UserRepository repository;

    @Mock
    BCryptPasswordEncoder passwordEncoder;

    @InjectMocks
    UserService service;

    final Long ID_1L = 1L;
    final String EMAIL = "email@example.com";
    final String USERNAME = "username";
    final String PASSWORD = "password";
    User testUser = new User(ID_1L, EMAIL, USERNAME, PASSWORD, Roles.USER, new ArrayList<>());

    @Test
    @DisplayName("Should initialize mock objects properly")
    public void shouldVerifyMockObjectsNotNull() {
        assertNotNull(repository);
        assertNotNull(service);
        assertNotNull(passwordEncoder);
        assertNotNull(testUser);
    }

    @Test
    public void findUserByEmailTest() {
        when(repository.findUserByEmail(EMAIL)).thenReturn(Optional.ofNullable(testUser));

        User user = service.findByEmail(EMAIL);

        assertEquals(testUser, user);
        assertThrows(UserNotFoundException.class, () -> service.findByEmail("nonexistentemail@example.com"));
    }

    @Test
    public void findUserByUsernameTest() {
        when(repository.findUserByUsername(USERNAME)).thenReturn(Optional.ofNullable(testUser));

        User user = service.findByUsername(USERNAME);

        assertEquals(testUser, user);
        assertThrows(UserNotFoundException.class, () -> service.findByUsername("nonExistentUsername"));
    }

    @Test
    public void registerTest() {
        UserRegisterDto registerDto = new UserRegisterDto(EMAIL, USERNAME, PASSWORD);
        User user = service.createUser(registerDto);

        verify(passwordEncoder).encode(PASSWORD);
        assertEquals(EMAIL, user.getEmail());
        assertEquals(USERNAME, user.getUsername());
        assertEquals(Roles.USER, user.getRole());
    }
}
