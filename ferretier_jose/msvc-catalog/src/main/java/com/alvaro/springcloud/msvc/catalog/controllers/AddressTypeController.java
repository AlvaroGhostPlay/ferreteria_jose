package com.alvaro.springcloud.msvc.catalog.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alvaro.springcloud.msvc.catalog.entities.AddressType;
import com.alvaro.springcloud.msvc.catalog.services.address_type.AddressTypeService;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/address-type")
public class AddressTypeController {

    @Autowired
    private AddressTypeService addressTypeService;

    @GetMapping("/page/{page}")
    public ResponseEntity<?> getAddresTypePage(@RequestParam int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return ResponseEntity.ok().body(addressTypeService.getAllAddresTypePage(pageable));
    }

    @GetMapping
    public ResponseEntity<?> getAddresTypeAll() {
        return ResponseEntity.ok().body(addressTypeService.getAllAddresType());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAddresType(@PathVariable String id) {
        Optional<AddressType> response = addressTypeService.getAddresTypeById(id);
        return response.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody AddressType request){
        Optional<AddressType> response = addressTypeService.save(request);
        return response.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(201).build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody AddressType request, @PathVariable String id){
        Optional<AddressType> response = addressTypeService.update(request, id);
        return response.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id){
        Optional<AddressType> response = addressTypeService.delete(id);
        return response.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}