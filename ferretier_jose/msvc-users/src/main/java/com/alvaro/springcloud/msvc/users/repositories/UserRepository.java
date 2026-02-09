package com.alvaro.springcloud.msvc.users.repositories;

import com.alvaro.springcloud.msvc.users.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByUsername(String username);
    //User findByEmail(String email);
}
