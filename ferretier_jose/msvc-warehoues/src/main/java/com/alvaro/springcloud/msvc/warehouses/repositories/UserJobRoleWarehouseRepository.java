package com.alvaro.springcloud.msvc.warehouses.repositories;

import com.alvaro.springcloud.msvc.warehouses.entities.UserJobRoleWarehouseId;
import com.alvaro.springcloud.msvc.warehouses.entities.UserWarehouse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJobRoleWarehouseRepository extends JpaRepository<UserWarehouse, UserJobRoleWarehouseId> {
}
