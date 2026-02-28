package com.alvaro.springcloud.msvc.warehouses.DTO.response;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

public record UserDTO(
        UUID userId,
        String username,
        Boolean enabled,
        Boolean mostChangePass,
        LocalDateTime createdAt,
        LocalDateTime passUpdateAt,
        Set<RoleDTO> roles
) {
}
