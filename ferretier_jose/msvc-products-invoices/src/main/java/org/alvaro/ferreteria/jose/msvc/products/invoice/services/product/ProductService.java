package org.alvaro.ferreteria.jose.msvc.products.invoice.services.product;

import java.util.UUID;

import org.alvaro.ferreteria.jose.msvc.products.invoice.dto.response.ProductDTO;
import org.alvaro.ferreteria.jose.msvc.products.invoice.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductService {
    Mono<Page<ProductDTO>> GetAllProductsPage(Pageable pageable);
    Flux<ProductDTO> getAllProducts();
    Mono<ProductDTO> getProductById(UUID id);
    Mono<ProductDTO> saveProduct(Product requestProduct);
    Mono<ProductDTO> updateProduct(Product requestProduct, UUID id);
    Mono<ProductDTO> deleteProductById(UUID id);
}
