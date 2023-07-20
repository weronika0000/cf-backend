package com.codersfactory.user.dto;

import java.util.List;

public record UserDto(
        Long id,
        String email,
        String username,
        List<String> role
) {
}
