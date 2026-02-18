package com.alvaro.springcloud.msvc.warehouses.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alvaro.springcloud.msvc.warehouses.entities.JobRole;

public interface JobRoleRepository extends JpaRepository<JobRole, UUID>{
    
}
