package com.alvaro.springcloud.msvc.catalog.controllers;

import com.alvaro.springcloud.msvc.catalog.entities.Gener;
import com.alvaro.springcloud.msvc.catalog.services.gener.GenerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/gener")
public class GenerController {
    @Autowired
    private GenerService generService;

    @GetMapping("/page/{page}")
    public ResponseEntity<?> getAllGenerTypePage(@RequestParam int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return ResponseEntity.ok().body(generService.getAllGenersPage(pageable));
    }

    @GetMapping
    public ResponseEntity<?> getGenerTypeAll() {
        return ResponseEntity.ok().body(generService.getAllGeners());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getGenerType(@PathVariable String id) {
        Optional<Gener> response = generService.getGenerById(id);
        return response.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Gener request){
        Optional<Gener> response = generService.save(request);
        return response.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(201).build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Gener request, @PathVariable String id){
        Optional<Gener> response = generService.update(request, id);
        return response.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id){
        Optional<Gener> response = generService.delete(id);
        return response.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}