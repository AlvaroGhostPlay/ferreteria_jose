package com.alvaro.springcloud.msvc.catalog.controllers;

import com.alvaro.springcloud.msvc.catalog.entities.MethodPaymment;
import com.alvaro.springcloud.msvc.catalog.services.method_paymmet.MethodPaymmetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/method-paymmets")
public class MethodPaymentsController {

    @Autowired
    private MethodPaymmetService methodPaymmetService;

    @GetMapping("/page/{page}")
    public ResponseEntity<?> getAllMethodPaymmetPage(@RequestParam int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return ResponseEntity.ok().body(methodPaymmetService.getAllMethodPaymmetPage(pageable));
    }

    @GetMapping
    public ResponseEntity<?> getMethodPaymmetAll() {
        return ResponseEntity.ok().body(methodPaymmetService.getAllMethodPayments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getMethodPaymmet(@PathVariable String id) {
        Optional<MethodPaymment> response = methodPaymmetService.getMethodPaymmetById(id);
        return response.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody MethodPaymment request){
        Optional<MethodPaymment> response = methodPaymmetService.save(request);
        return response.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(201).build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody MethodPaymment request, @PathVariable String id){
        Optional<MethodPaymment> response = methodPaymmetService.update(request, id);
        return response.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id){
        Optional<MethodPaymment> response = methodPaymmetService.delete(id);
        return response.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}