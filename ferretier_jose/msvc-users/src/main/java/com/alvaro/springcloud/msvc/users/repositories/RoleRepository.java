package com.alvaro.springcloud.msvc.users.repositories;

import com.alvaro.springcloud.msvc.users.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;


public interface RoleRepository extends JpaRepository<Role, UUID> {
    @Query("""
        select r from Role r 
        where r.roleName = ?1
    """)
    Optional<Role> findByName(String name);
}