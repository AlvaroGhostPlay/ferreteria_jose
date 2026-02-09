package com.alvaro.springcloud.msvc.users.services;

import com.alvaro.springcloud.msvc.users.dto.request.UserDTO;
import com.alvaro.springcloud.msvc.users.dto.response.UserResponseDTO;
import com.alvaro.springcloud.msvc.users.entities.Role;
import com.alvaro.springcloud.msvc.users.entities.User;
import com.alvaro.springcloud.msvc.users.repositories.RoleRepository;
import com.alvaro.springcloud.msvc.users.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;


    /*@Transactional(readOnly = true)
    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.of(userRepository.findByEmail(email));
    }*/

    @Transactional(readOnly = true)
    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Page<User> findAll(Pageable page) {
        return userRepository.findAll(page);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<User> findById(UUID id) {
        return userRepository.findById(id);
    }

    /*Este metodo es para cambiar la contraseña*/
    @Override
    @Transactional
    public Optional<User> changePass(UserDTO user, UUID id) {
        Optional<User> userOptional = userRepository.findByUsername(user.username());
        if (userOptional.isPresent()) {
            if (userOptional.get().getPassword().equals(passwordEncoder.encode(user.password()))) {
                userOptional.get().setPassword(passwordEncoder.encode(user.password()));
            } else {
                new Exception();
            }
        }
        return userOptional;
    }


    /*Este metodo es para crear el usuario*/
    @Transactional
    @Override
    public Optional<User> save(User user) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Set<Role> role = new HashSet<>();
        if (user.getRoles() != null) {
            for (Role role1 : user.getRoles()) {
                if (role1 != null) {
                    roleRepository.findByName(role1.getRoleName()).ifPresent(role::add);
                }
            }
        }
        user.setRoles(role);
        return Optional.of(userRepository.save(user));
    }

    @Transactional
    @Override
    public Optional<UserResponseDTO> update(User user, UUID id) {
        Optional<User> userOptional = userRepository.findById(id);
        Optional<UserResponseDTO> userResponse = Optional.empty();
        Set<Role> role = new HashSet<>();
        if (userOptional.isPresent()) {
            if (user.getRoles() != null) {
                for (Role role1 : user.getRoles()) {
                    if (role1 != null) {
                        roleRepository.findByName(role1.getRoleName()).ifPresent(role::add);
                    }
                }
            }
            userResponse.get().setRoles(role);
            userOptional.get().setRoles(role);
            userResponse.get().setEnabled(user.isEnabled());
            userOptional.get().setEnabled(user.isEnabled());
            userOptional.get().setPassUpdateAt(LocalDateTime.now());
            userResponse.get().setPassUpdateAt(LocalDateTime.now());
            userOptional.get().setMostChangePass(user.getMostChangePass());
            userResponse.get().setMostChangePass(user.getMostChangePass());
            userRepository.save(userOptional.get());
        }

        return userResponse;
    }


    @Transactional
    @Override
    public Optional<User> delete(UUID id) {
        Optional<User> userOptional = userRepository.findById(id);
        userOptional.ifPresent(u -> userRepository.delete(u));
        return userOptional;
    }


    /*private Set<Role> getRoles(User user) {

        Set<Role> roles = new HashSet<>();
        if (user.isAdmin()) {
            Optional<Role> roleAdmin = roleRepository.findByName("ROLE_ADMIN");
            roleAdmin.ifPresent(roles::add);
        }
        Optional<Role> roleOptional = roleRepository.findByName("ROLE_USER");
        roleOptional.ifPresent(roles::add);

        return roles;
    }*/
}
