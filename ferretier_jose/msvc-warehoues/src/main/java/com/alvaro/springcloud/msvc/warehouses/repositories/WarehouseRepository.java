package com.alvaro.springcloud.msvc.warehouses.repositories;

import com.alvaro.springcloud.msvc.warehouses.entities.Warehouse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;


public interface WarehouseRepository extends JpaRepository<Warehouse, UUID> {
    Optional<Warehouse> findByWarehouseId(UUID warehouseId);
    Optional<Warehouse> deleteByWarehouseId(UUID warehouseId);
    Page<Warehouse> findAll(Pageable pageable);
}
