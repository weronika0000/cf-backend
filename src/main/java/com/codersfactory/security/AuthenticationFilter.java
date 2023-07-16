package com.codersfactory.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.codersfactory.user.CustomUserDetails;
import com.codersfactory.user.CustomUserDetailsService;
import com.codersfactory.user.dto.UserDto;
import com.codersfactory.user.dto.UserLoginDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.Date;
import java.util.Optional;

class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService service;

    AuthenticationFilter(AuthenticationManager authenticationManager, CustomUserDetailsService service) {
        this.authenticationManager = authenticationManager;
        this.service = service;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        try {
            UserLoginDto userDto = new ObjectMapper().readValue(request.getInputStream(), UserLoginDto.class);
            Authentication authentication = new UsernamePasswordAuthenticationToken
                    (userDto.email(), userDto.password());
            return authenticationManager.authenticate(authentication);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException {
        String token = JWT.create()
                .withSubject(authResult.getName())
                .withExpiresAt(new Date(System.currentTimeMillis() + 7200000))
                .withClaim("Roles", authResult.getAuthorities().stream().map(GrantedAuthority::toString).toList())
                .sign(Algorithm.HMAC512(SecurityConfiguration.secretKey));
        CustomUserDetails user = (CustomUserDetails) service.loadUserByUsername(authResult.getName());
        UserDto dto = new UserDto(user.getId(),
                user.getUsername(),
                user.getAuthorities().stream().map(GrantedAuthority::toString).toList());
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                user.getUsername(),
                Optional.empty(),
                user.getAuthorities().stream().map(Object::toString).map(SimpleGrantedAuthority::new).toList()
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        response.setStatus(HttpServletResponse.SC_OK);
        response.addCookie(createCookie(token));
        response.getWriter().write(new ObjectMapper().writeValueAsString(dto));
        response.flushBuffer();
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request,
                                              HttpServletResponse response,
                                              AuthenticationException failed) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write(failed.getMessage());
        response.getWriter().flush();
    }

    private Cookie createCookie(String token) {
        Cookie cookie = new Cookie("Authorization", token);
        cookie.setMaxAge(86400);
        cookie.setSecure(true);
        cookie.setPath("/");
        return cookie;
    }
}
