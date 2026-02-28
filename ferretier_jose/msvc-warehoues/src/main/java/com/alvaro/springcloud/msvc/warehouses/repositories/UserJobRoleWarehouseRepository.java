package com.alvaro.springcloud.msvc.warehouses.repositories;

import com.alvaro.springcloud.msvc.warehouses.entities.UserJobRoleWarehouseId;
import com.alvaro.springcloud.msvc.warehouses.entities.UserWarehouse;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserJobRoleWarehouseRepository extends JpaRepository<UserWarehouse, UserJobRoleWarehouseId> {
    List<UserWarehouse> findAllById_IdWarehouse(UUID warehouseId);
    List<UserWarehouse> findAllById_IdUser(UUID warehouseId);

    @Query("""
            select uwj from UserWarehouse as uwj
            where uwj.id.idUser = :userId
            """)
    Set<UserWarehouse> getRoleByUserId(UUID userId);  
}
