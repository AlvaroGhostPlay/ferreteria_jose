package com.alvaro.springcloud.msvc.users.dto.request;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record UserDTO(
        @NotBlank
        UUID userID,
        @NotBlank
        String username,
        @NotBlank
        String password,
        @NotBlank
        String passConf
) {
}
