package com.alvaro.springcloud.msvc.users.controllers;

import com.alvaro.springcloud.msvc.users.entities.Role;
import com.alvaro.springcloud.msvc.users.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @GetMapping("/page/{page}")
    public ResponseEntity<?> rolePage(@PathVariable int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return ResponseEntity.ok().body(roleService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> roleById(@PathVariable UUID id) {
        Optional<Role> roleOPtional = roleService.findById(id);
        return roleOPtional.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<?> roleUsers() {
        return ResponseEntity.ok().body(roleService.findAll());
    }

    @PostMapping
    public ResponseEntity<?> createRole(@RequestBody Role role) {
        Optional<Role> userOPtional = roleService.save(role);
        return userOPtional.map(u -> ResponseEntity.status(HttpStatusCode.valueOf(201)).body(u))
                .orElseGet(() -> ResponseEntity.status(HttpStatusCode.valueOf(203)).build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable UUID id, @RequestBody Role role) {
        Optional<Role> userOPtional = roleService.update(role, id);
        return userOPtional.map(user1 -> ResponseEntity.status(HttpStatus.CREATED).body(user1))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable UUID id) {
        Optional<Role> userOPtional = roleService.delete(id);
        return userOPtional.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
