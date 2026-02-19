package com.alvaro.springcloud.msvc.users.services;

import com.alvaro.springcloud.msvc.users.dto.request.UserDTO;
import com.alvaro.springcloud.msvc.users.dto.response.UserResponseDTO;
import com.alvaro.springcloud.msvc.users.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {
    //Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);
    Optional<UserResponseDTO> save(User user);
    List<User> findAll();
    Optional<UserResponseDTO> update(User user, UUID id);
    Optional<UserResponseDTO> delete(UUID id);
    Page<User> findAll(Pageable page);
    Optional<User> findById(UUID id);
    Optional<User> changePass(UserDTO user, UUID id);
}
