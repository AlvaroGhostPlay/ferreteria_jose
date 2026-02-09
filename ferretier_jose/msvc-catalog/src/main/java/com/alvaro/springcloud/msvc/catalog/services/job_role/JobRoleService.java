package com.alvaro.springcloud.msvc.catalog.services.job_role;

import com.alvaro.springcloud.msvc.catalog.entities.JobRole;
import com.alvaro.springcloud.msvc.catalog.entities.SocialStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface JobRoleService {

    List<JobRole> getAllJobRole();
    Page<JobRole> getAllJobRolePage(Pageable pageable);
    Optional<JobRole> getJobRoleById(UUID id);
    Optional<JobRole> save(JobRole request);
    Optional<JobRole> update(JobRole request, UUID id);
    Optional<JobRole> delete(UUID id);
}
