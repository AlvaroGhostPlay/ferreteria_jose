package com.alvaro.springcloud.msvc.catalog.services.job_role;

import com.alvaro.springcloud.msvc.catalog.entities.JobRole;
import com.alvaro.springcloud.msvc.catalog.entities.SocialStatus;
import com.alvaro.springcloud.msvc.catalog.repositories.JobRoleRepository;
import com.alvaro.springcloud.msvc.catalog.repositories.SocialStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class JobRoleServiceImpl implements JobRoleService {

    @Autowired
    private JobRoleRepository jobRoleRepository;

    @Transactional(readOnly = true)
    @Override
    public List<JobRole> getAllJobRole() {
        return jobRoleRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Page<JobRole> getAllJobRolePage(Pageable pageable) {
        return jobRoleRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<JobRole> getJobRoleById(UUID id) {
        return jobRoleRepository.findByJobRoleId(id);
    }

    @Transactional
    @Override
    public Optional<JobRole> save(JobRole request) {
        JobRole jobRole = new JobRole();
        jobRole.setJobRole(request.getJobRole());
        jobRole.setJobRoleId(request.getJobRoleId());
        jobRoleRepository.save(jobRole);
        return Optional.of(jobRole);
    }

    @Transactional
    @Override
    public Optional<JobRole> update(JobRole request, UUID id) {
        Optional<JobRole> jobRole = jobRoleRepository.findByJobRoleId(id);
        if (jobRole.isPresent()) {
            jobRole.get().setJobRoleId(request.getJobRoleId());
            jobRole.get().setJobRole(request.getJobRole());
            return jobRole;
        }
        return Optional.empty();
    }

    @Transactional
    @Override
    public Optional<JobRole> delete(UUID id) {
        Optional<JobRole> jobOptional = jobRoleRepository.findByJobRoleId(id);
        if (jobOptional.isPresent()) {
            return jobRoleRepository.deleteByJobRoleId(id);
        }
        return Optional.empty();
    }
}
