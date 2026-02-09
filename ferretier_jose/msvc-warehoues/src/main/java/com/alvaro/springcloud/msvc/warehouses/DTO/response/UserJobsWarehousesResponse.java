package com.alvaro.springcloud.msvc.warehouses.DTO.response;

import com.alvaro.springcloud.msvc.warehouses.entities.Warehouse;

import java.util.Set;

public class UserJobsWarehousesResponse {
    private UserDTO user;
    private Set<Warehouse> warehouses;
    private Set<JobRoleDTO> jobRoles;

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public Set<Warehouse> getWarehouses() {
        return warehouses;
    }

    public void setWarehouses(Set<Warehouse> warehouses) {
        this.warehouses = warehouses;
    }

    public Set<JobRoleDTO> getJobRoles() {
        return jobRoles;
    }

    public void setJobRoles(Set<JobRoleDTO> jobRoles) {
        this.jobRoles = jobRoles;
    }
}
