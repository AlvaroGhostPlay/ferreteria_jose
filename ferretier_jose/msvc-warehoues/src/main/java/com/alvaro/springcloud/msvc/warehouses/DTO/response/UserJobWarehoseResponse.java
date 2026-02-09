package com.alvaro.springcloud.msvc.warehouses.DTO.response;

import com.alvaro.springcloud.msvc.warehouses.entities.Warehouse;

public class UserJobWarehoseResponse {
    private UserDTO user;
    private JobRoleDTO jobRole;
    private Warehouse warehouse;

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public JobRoleDTO getJobRole() {
        return jobRole;
    }

    public void setJobRole(JobRoleDTO jobRole) {
        this.jobRole = jobRole;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }
}
