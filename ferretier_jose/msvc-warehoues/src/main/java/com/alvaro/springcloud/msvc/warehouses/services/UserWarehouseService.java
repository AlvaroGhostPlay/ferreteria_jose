package com.alvaro.springcloud.msvc.warehouses.services;

import com.alvaro.springcloud.msvc.warehouses.DTO.response.UserJobsWarehousesResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserWarehouseService{
    Optional<UserJobsWarehousesResponse> getUserJobWarehouse(UUID id);
    List<UserJobsWarehousesResponse> getAllUserJobWarehouses();
    Page<UserJobsWarehousesResponse> getUserJobWarehouses(Pageable pageable);

}
