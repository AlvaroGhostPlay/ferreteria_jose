package com.alvaro.springcloud.msvc.users.controllers;

import com.alvaro.springcloud.msvc.users.dto.request.UserDTO;
import com.alvaro.springcloud.msvc.users.dto.response.UserResponseDTO;
import com.alvaro.springcloud.msvc.users.entities.User;
import com.alvaro.springcloud.msvc.users.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/page/{page}")
    public ResponseEntity<?> userPage(@PathVariable int page) {
        Pageable pageable = PageRequest.of(page, 10);
        logger.info("User Controller::userPage");
        return ResponseEntity.ok().body(userService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> userById(@PathVariable UUID id) {
        Optional<User> userOPtional = userService.findById(id);
        logger.info("User Controller::userById");
        return userOPtional.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<?> getUsers() {
        logger.info("User Controller::getUsers");
        return ResponseEntity.ok().body(userService.findAll());
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<?> getUser(@PathVariable String username) {
        Optional<User> userOPtional = userService.findByUsername(username);
        logger.info("User Controller::getUser");
        return userOPtional.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /*@GetMapping("/email/{email}")
    public ResponseEntity<?> getEmail(@PathVariable String email) {
        Optional<User> userOPtional = userService.findByEmail(email);
        logger.info("User Controller::getEmail");
        return userOPtional.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }*/

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user) {
        Optional<User> userOPtional = userService.save(user);
        logger.info("User Controller::createUser: creando {}", userOPtional.get());
        return userOPtional.map(u -> ResponseEntity.status(HttpStatusCode.valueOf(201)).body(u))
                .orElseGet(() -> ResponseEntity.status(HttpStatusCode.valueOf(203)).build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable UUID id, @RequestBody User user) {
        Optional<UserResponseDTO> userOPtional = userService.update(user, id);
        logger.info("User Controller::createUser: updateUser {}", userOPtional.get());
        return userOPtional.map(user1 -> ResponseEntity.status(HttpStatus.CREATED).body(user1))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/change/{id}")
    public ResponseEntity<?> changePass(@PathVariable UUID id, @RequestBody UserDTO user) {
        Optional<User> userOPtional = userService.changePass(user, id);
        logger.info("User Controller::createUser: updateUser {}", userOPtional.get());
        return userOPtional.map(user1 -> ResponseEntity.status(HttpStatus.CREATED).body(user1))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable UUID id) {
        Optional<User> userOPtional = userService.delete(id);
        logger.info("User Controller::deleteUser: Eliminando {}", userOPtional.get());
        return userOPtional.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
