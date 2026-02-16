package org.alvaro.ferreteria.jose.msvc.products.invoice.services.product;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.alvaro.ferreteria.jose.msvc.products.invoice.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    Optional<Product> getProductById(UUID id);
    List<Product> getAllProducts();
    Page<Product> GetAllProductsPage(Pageable pageable);
    Optional<Product> saveProduct (Product requestProduct);
    Optional<Product> updateProduct(Product requestProduct, UUID id);
    Optional<Product> deleteProductById(UUID id); 
}
