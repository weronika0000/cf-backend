package com.codersfactory.user.dto;

import jakarta.validation.constraints.NotBlank;

public record UserRegisterDto(
        @NotBlank String email,
        @NotBlank String username,
        @NotBlank String password

) {
}
