package com.alvaro.springcloud.msvc.catalog.controllers;

import com.alvaro.springcloud.msvc.catalog.entities.Category;
import com.alvaro.springcloud.msvc.catalog.services.category.CategorySercice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategorySercice categoryServSercice;

    @GetMapping("/page/{page}")
    public ResponseEntity<?> getAllCategoryPage(@RequestParam int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return ResponseEntity.ok().body(categoryServSercice.getAllCategoriesPage(pageable));
    }

    @GetMapping
    public ResponseEntity<?> getCategoryAll() {
        return ResponseEntity.ok().body(categoryServSercice.getAllCategories());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCategory(@PathVariable String id) {
        Optional<Category> response = categoryServSercice.getCategoryById(id);
        return response.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Category request){
        Optional<Category> response = categoryServSercice.save(request);
        return response.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(201).build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Category request, @PathVariable String id){
        Optional<Category> response = categoryServSercice.update(request, id);
        return response.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id){
        Optional<Category> response = categoryServSercice.delete(id);
        return response.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}