package com.codersfactory.security;

import com.codersfactory.user.UserService;
import com.codersfactory.user.dto.UserRegisterDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
class AuthorizationController {

    UserService service;

    @PostMapping("/register")
    public ResponseEntity<HttpStatus> registerUser(@RequestBody UserRegisterDto dto) {
        service.registerUser(dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
