package com.alvaro.springcloud.msvc.catalog.controllers;

import com.alvaro.springcloud.msvc.catalog.entities.JobRole;
import com.alvaro.springcloud.msvc.catalog.services.job_role.JobRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/job-role")
public class JobRoleController {

    @Autowired
    private JobRoleService jobRoleService;

    @GetMapping("/page/{page}")
    public ResponseEntity<?> getAllJobRolePage(@RequestParam int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return ResponseEntity.ok().body(jobRoleService.getAllJobRolePage(pageable));
    }

    @GetMapping
    public ResponseEntity<?> getJobRoleAll() {
        return ResponseEntity.ok().body(jobRoleService.getAllJobRole());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getJobRole(@PathVariable UUID id) {
        Optional<JobRole> response = jobRoleService.getJobRoleById(id);
        return response.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getJobRoleByClient(@PathVariable UUID id) {
        Optional<JobRole> response = jobRoleService.getJobRoleById(id);
        return response.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody JobRole request){
        Optional<JobRole> response = jobRoleService.save(request);
        return response.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(201).build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody JobRole request, @PathVariable UUID id){
        Optional<JobRole> response = jobRoleService.update(request, id);
        return response.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id){
        Optional<JobRole> response = jobRoleService.delete(id);
        return response.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}