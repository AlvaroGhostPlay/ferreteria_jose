package com.alvaro.springcloud.msvc.warehouses.DTO.request;

import java.util.UUID;

public record CreateUserWarehouseJobRole(
    UUID idUser,
    UUID idJobRole,
    UUID idWarehouse
) {
} 