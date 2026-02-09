package com.alvaro.springcloud.msvc.catalog.repositories;

import com.alvaro.springcloud.msvc.catalog.entities.JobRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface JobRoleRepository extends JpaRepository<JobRole, UUID> {
    Optional<JobRole> deleteByJobRoleId(UUID jobRoleId);
    Optional<JobRole> findByJobRoleId(UUID jobRoleId);
}
