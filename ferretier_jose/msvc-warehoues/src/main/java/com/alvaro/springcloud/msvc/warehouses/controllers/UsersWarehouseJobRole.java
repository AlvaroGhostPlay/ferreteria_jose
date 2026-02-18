package com.alvaro.springcloud.msvc.warehouses.controllers;

import java.util.Optional;
import java.util.UUID;

import org.apache.hc.core5.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alvaro.springcloud.msvc.warehouses.DTO.request.CreateUserWarehouseJobRole;
import com.alvaro.springcloud.msvc.warehouses.DTO.response.UserWarhouseDTO;
import com.alvaro.springcloud.msvc.warehouses.services.UserWarehouseService;

import reactor.core.publisher.Mono;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/roles")
public class UsersWarehouseJobRole {
    
    @Autowired
    private UserWarehouseService userWarehouseService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserByWarehouse(@PathVariable UUID id){
        return ResponseEntity.ok().body(userWarehouseService.getUsersByWarehouse(id));
    }

@PostMapping
public Mono<ResponseEntity<UserWarhouseDTO>> savetUserWarehouseJobRole(
        @RequestBody CreateUserWarehouseJobRole request) {

    return userWarehouseService.saveUsersByWarehouse(request)
            .map(dto -> ResponseEntity.status(HttpStatus.SC_CREATED).body(dto))
            .switchIfEmpty(Mono.just(ResponseEntity.noContent().build()));
}
}
