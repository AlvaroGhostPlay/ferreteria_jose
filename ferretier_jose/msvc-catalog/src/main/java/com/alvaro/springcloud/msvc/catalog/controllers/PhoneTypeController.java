package com.alvaro.springcloud.msvc.catalog.controllers;

import com.alvaro.springcloud.msvc.catalog.entities.PhoneType;
import com.alvaro.springcloud.msvc.catalog.services.phone_type.PhoneTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/phone-type")
public class PhoneTypeController {
    @Autowired
    private PhoneTypeService phoneTypeService;

    @GetMapping("/page/{page}")
    public ResponseEntity<?> getAllGenerTypePage(@RequestParam int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return ResponseEntity.ok().body(phoneTypeService.getAllPhonesTypesPage(pageable));
    }

    @GetMapping
    public ResponseEntity<?> getGenerTypeAll() {
        return ResponseEntity.ok().body(phoneTypeService.getAllPhonesTypes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getGenerType(@PathVariable String id) {
        Optional<PhoneType> response = phoneTypeService.getPhonesById(id);
        return response.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody PhoneType request){
        Optional<PhoneType> response = phoneTypeService.save(request);
        return response.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(201).build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody PhoneType request, @PathVariable String id){
        Optional<PhoneType> response = phoneTypeService.update(request, id);
        return response.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id){
        Optional<PhoneType> response = phoneTypeService.delete(id);
        return response.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}