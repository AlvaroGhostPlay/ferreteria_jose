package com.alvaro.springcloud.msvc.catalog.controllers;

import com.alvaro.springcloud.msvc.catalog.entities.PersonType;
import com.alvaro.springcloud.msvc.catalog.services.person_type.PersonTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/person-type")
public class PersonTypeController {
    @Autowired
    private PersonTypeService personTypeService;

    @GetMapping("/page/{page}")
    public ResponseEntity<?> getAllGenerTypePage(@RequestParam int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return ResponseEntity.ok().body(personTypeService.getAllPersonTypesPage(pageable));
    }

    @GetMapping
    public ResponseEntity<?> getGenerTypeAll() {
        return ResponseEntity.ok().body(personTypeService.getAllPersonTypes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getGenerType(@PathVariable String id) {
        Optional<PersonType> response = personTypeService.getPersonById(id);
        return response.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody PersonType request){
        Optional<PersonType> response = personTypeService.save(request);
        return response.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(201).build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody PersonType request, @PathVariable String id){
        Optional<PersonType> response = personTypeService.update(request, id);
        return response.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id){
        Optional<PersonType> response = personTypeService.delete(id);
        return response.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
