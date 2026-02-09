package com.alvaro.springcloud.msvc.warehouses.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "users_warehouses")
public class UserWarehouse {

    @EmbeddedId
    private UserJobRoleWarehouseId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_warehouse", referencedColumnName = "id", insertable = false, updatable = false)
    private Warehouse warehouse;

    public UserJobRoleWarehouseId getId() {
        return id;
    }

    public void setId(UserJobRoleWarehouseId id) {
        this.id = id;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }
}
