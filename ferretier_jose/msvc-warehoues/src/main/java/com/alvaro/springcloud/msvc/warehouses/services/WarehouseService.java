package com.alvaro.springcloud.msvc.warehouses.services;

import com.alvaro.springcloud.msvc.warehouses.entities.Warehouse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface WarehouseService {
    Optional<Warehouse> getWarehouse(UUID id);
    Page<Warehouse> getWarehouses(Pageable pageable);
    List<Warehouse> getAllWarehouses();
    Optional<Warehouse> save(Warehouse warehouse);
    Optional<Warehouse> update(Warehouse warehouse, UUID id);
    Optional<Warehouse> deleteById(UUID id);

}
