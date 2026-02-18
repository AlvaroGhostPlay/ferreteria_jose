package com.alvaro.springcloud.msvc.warehouses.DTO.response;

import java.util.Set;

public record UserWarhouseDTO(
    UserDTO user,
    Set<JobRoleDTO> jobRole
) {
    
}
