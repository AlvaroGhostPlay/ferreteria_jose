package com.alvaro.springcloud.msvc.warehouses.services;

import com.alvaro.springcloud.msvc.warehouses.DTO.request.CreateUserWarehouseJobRole;
import com.alvaro.springcloud.msvc.warehouses.DTO.response.UserDTO;
import com.alvaro.springcloud.msvc.warehouses.DTO.response.UserJobsWarehousesResponse;
import com.alvaro.springcloud.msvc.warehouses.DTO.response.UserWarhouseDTO;

import reactor.core.publisher.Mono;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserWarehouseService{
    Optional<UserJobsWarehousesResponse> getUserJobWarehouse(UUID id);
    List<UserJobsWarehousesResponse> getAllUserJobWarehouses();
    Page<UserJobsWarehousesResponse> getUserJobWarehouses(Pageable pageable);
    Optional<UserWarhouseDTO> getJobRolesByUserId(UUID id);

    List<UserWarhouseDTO> getUsersByWarehouse(UUID warehouseId);
    Mono<UserWarhouseDTO> saveUsersByWarehouse(CreateUserWarehouseJobRole warehouseId);

}
