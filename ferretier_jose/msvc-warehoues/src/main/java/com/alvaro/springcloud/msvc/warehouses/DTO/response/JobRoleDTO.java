package com.alvaro.springcloud.msvc.warehouses.DTO.response;

import java.util.UUID;

public record JobRoleDTO(
        UUID jobRoleId,
        String jobRole
) {}
