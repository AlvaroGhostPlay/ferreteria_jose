package org.alvaro.ferreteria.jose.msvc.products.invoice.controllers;

import java.util.UUID;

import org.alvaro.ferreteria.jose.msvc.products.invoice.dto.response.ProductDTO;
import org.alvaro.ferreteria.jose.msvc.products.invoice.entities.Product;
import org.alvaro.ferreteria.jose.msvc.products.invoice.services.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequestMapping("/product")
@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/page/{page}")
    public Mono<ResponseEntity<Page<ProductDTO>>> getAllProductsPage(@PathVariable int page) {
        Pageable paginator = PageRequest.of(page, 10);
        return productService.GetAllProductsPage(paginator)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.noContent().build());
    }

    @GetMapping
    public Flux<ProductDTO> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<ProductDTO>> getProductById(@PathVariable UUID id) {
        return productService.getProductById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Mono<ResponseEntity<ProductDTO>> saveProduct(@Valid @RequestBody Product reqProduct) {
        return productService.saveProduct(reqProduct)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.noContent().build());
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<ProductDTO>> updateProduct(@Valid @RequestBody Product reqProduct, @PathVariable UUID id) {
        return productService.updateProduct(reqProduct, id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<ProductDTO>> deletProduct(@PathVariable UUID id) {
        return productService.deleteProductById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}