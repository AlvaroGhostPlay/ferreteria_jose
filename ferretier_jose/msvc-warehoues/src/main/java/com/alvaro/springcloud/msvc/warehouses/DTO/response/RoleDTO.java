package com.alvaro.springcloud.msvc.warehouses.DTO.response;

import java.util.UUID;

public record RoleDTO(
        UUID roleId,
        String roleName,
        UUID id
) {
}
