package com.alvaro.springcloud.msvc.warehouses.repositories;

import com.alvaro.springcloud.msvc.warehouses.entities.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface WarehouseRepository extends JpaRepository<Warehouse, UUID> {
}
