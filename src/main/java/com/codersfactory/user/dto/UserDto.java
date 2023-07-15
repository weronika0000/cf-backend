package com.codersfactory.user.dto;

import java.util.List;

public record UserDto(
        Long email,
        String username,
        List<String> role
) {
}
