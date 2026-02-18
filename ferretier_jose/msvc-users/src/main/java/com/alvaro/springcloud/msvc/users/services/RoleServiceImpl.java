package com.alvaro.springcloud.msvc.users.services;

import com.alvaro.springcloud.msvc.users.entities.Role;
import com.alvaro.springcloud.msvc.users.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;


    @Transactional
    @Override
    public Optional<Role> save(Role role) {
        return Optional.of(roleRepository.save(role));
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Optional<Role> update(Role user, UUID id) {
        Optional<Role> role = roleRepository.findById(id);
        if (role.isPresent()) {
            role.get().setRoleName(user.getRoleName());
            return Optional.of(roleRepository.save(role.get()));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Role> delete(UUID id) {
        Optional<Role> role = roleRepository.findById(id);
        if (role.isPresent()) {
            roleRepository.delete(role.get());
            return role;
        }
        return Optional.empty();
    }

    @Override
    public Page<Role> findAll(Pageable page) {
        return roleRepository.findAll(page);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Role> findById(UUID id) {
        return roleRepository.findByRoleId(id);
    }
}
