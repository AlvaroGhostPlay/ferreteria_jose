package com.alvaro.springcloud.msvc.warehouses.controllers;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alvaro.springcloud.msvc.warehouses.entities.Warehouse;
import com.alvaro.springcloud.msvc.warehouses.services.WarehouseService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/warehouse")
public class WarehouseController {

    @Autowired
    private WarehouseService warehouseService;

    @GetMapping
    public ResponseEntity<?> getAllWarehouses() {
        return ResponseEntity.ok().body(warehouseService.getAllWarehouses());
    }
    
    @GetMapping("/page/{page}")
    public ResponseEntity<?> getAllWarehousesPage(@PathVariable int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return ResponseEntity.ok().body(warehouseService.getWarehouses(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getWarehouseById(@PathVariable UUID id) {
        Optional<Warehouse> response = this.warehouseService.getWarehouse(id);
        return response.map(ResponseEntity::ok)
        .orElseGet(
            () -> ResponseEntity.notFound().build()
        );
    }

    @PostMapping
    public ResponseEntity<?> saveWarehouse(@RequestBody Warehouse request) {
        Optional<Warehouse> response = this.warehouseService.save(request);
        return response.map(ResponseEntity::ok)
        .orElseGet(
            () -> ResponseEntity.noContent().build()
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateWarehouse(@RequestBody Warehouse request, @PathVariable UUID id) {
        Optional<Warehouse> response = this.warehouseService.update(request, id);
        return response.map(ResponseEntity::ok)
        .orElseGet(
            () -> ResponseEntity.noContent().build()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteWarehouse(@PathVariable UUID id) {
        Optional<Warehouse> response = this.warehouseService.deleteById(id);
        return response.map(ResponseEntity::ok)
        .orElseGet(
            () -> ResponseEntity.noContent().build()
        );
    }
    
}
