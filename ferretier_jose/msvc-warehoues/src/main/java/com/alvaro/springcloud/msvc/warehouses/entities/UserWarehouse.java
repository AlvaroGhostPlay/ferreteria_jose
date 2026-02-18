package com.alvaro.springcloud.msvc.warehouses.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "users_warehouses")
public class UserWarehouse {

    @EmbeddedId
    private UserJobRoleWarehouseId id;


    @ManyToOne
    @MapsId("idWarehouse")
    @JoinColumn(name = "id_warehouse")
    private Warehouse warehouse;

    @ManyToOne
    @MapsId("idJobRole")
    @JoinColumn(name = "id_job_role")
    private JobRole jobRole;

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

    public JobRole getJobRole() {
        return jobRole;
    }

    public void setJobRole(JobRole jobRole) {
        this.jobRole = jobRole;
    }
}
