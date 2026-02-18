package com.alvaro.springcloud.msvc.warehouses.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

@Entity
@Table(name = "jobs_roles")
public class JobRole {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "job_role_id")
    private UUID jobRoleId;

    @NotBlank
    @Column(name = "job_role", unique = true)
    private String jobRole;

    public UUID getJobRoleId() {
        return jobRoleId;
    }

    public void setJobRoleId(UUID jobRoleId) {
        this.jobRoleId = jobRoleId;
    }

    public @NotBlank String getJobRole() {
        return jobRole;
    }

    public void setJobRole(@NotBlank String jobRoleM) {
        this.jobRole = jobRoleM;
    }
}
