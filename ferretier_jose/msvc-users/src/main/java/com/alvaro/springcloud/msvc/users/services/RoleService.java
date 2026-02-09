package com.alvaro.springcloud.msvc.users.services;

import com.alvaro.springcloud.msvc.users.entities.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RoleService {
    Optional<Role> save(Role user);
    List<Role> findAll();
    Optional<Role> update(Role user, UUID id);
    Optional<Role> delete(UUID id);
    Page<Role> findAll(Pageable page);
    Optional<Role> findById(UUID id);
}
