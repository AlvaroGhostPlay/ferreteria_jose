package com.alvaro.springcloud.msvc.catalog.controllers;

import com.alvaro.springcloud.msvc.catalog.entities.Brand;
import com.alvaro.springcloud.msvc.catalog.services.brand.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/brand")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @GetMapping("/page/{page}")
    public ResponseEntity<?> getAllCategoryPage(@RequestParam int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return ResponseEntity.ok().body(brandService.getAllBrandPage(pageable));
    }

    @GetMapping
    public ResponseEntity<?> getCategoryAll() {
        return ResponseEntity.ok().body(brandService.getAllBrand());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCategory(@PathVariable String id) {
        Optional<Brand> response = brandService.getBrandById(id);
        return response.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Brand request){
        Optional<Brand> response = brandService.save(request);
        return response.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(201).build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Brand request, @PathVariable String id){
        Optional<Brand> response = brandService.update(request, id);
        return response.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id){
        Optional<Brand> response = brandService.delete(id);
        return response.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}