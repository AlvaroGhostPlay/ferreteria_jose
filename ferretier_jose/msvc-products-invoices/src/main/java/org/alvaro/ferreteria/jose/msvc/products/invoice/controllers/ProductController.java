package org.alvaro.ferreteria.jose.msvc.products.invoice.controllers;

import java.util.Optional;
import java.util.UUID;

import org.alvaro.ferreteria.jose.msvc.products.invoice.entities.Product;
import org.alvaro.ferreteria.jose.msvc.products.invoice.services.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RequestMapping("/product")
@RestController
@CrossOrigin("http://localhost:4200")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/page/{page}")
    public ResponseEntity<?> getAllProductsPage(@PathVariable int page){
        Pageable paginator = PageRequest.of(page, 10);
        return ResponseEntity.ok().body(productService.GetAllProductsPage(paginator));
    }

    @GetMapping
    public ResponseEntity<?> getAllProducts(){
        return ResponseEntity.ok(). body(productService.getAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable UUID id){
        Optional<Product> prOptional = productService.getProductById(id);
        return prOptional.map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> saveProduct(@Valid @RequestBody Product reuqProduct){
        Optional<Product> prodOptional = productService.saveProduct(reuqProduct);
        return prodOptional.map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.noContent().build());
    }

    @PutMapping("/{id}") 
    public ResponseEntity<?> updateProduct(@Valid @RequestBody Product reProduct, @PathVariable UUID id){
        Optional<Product> prodOptional = productService.updateProduct(reProduct, id);
        return prodOptional.map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletProduct(@PathVariable UUID id){
        Optional<Product> prodOptional = productService.deleteProductById(id);
        return prodOptional.map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
