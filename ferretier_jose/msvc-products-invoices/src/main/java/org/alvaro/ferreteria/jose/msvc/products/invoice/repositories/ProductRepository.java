package org.alvaro.ferreteria.jose.msvc.products.invoice.repositories;

import java.util.Optional;
import java.util.UUID;

import org.alvaro.ferreteria.jose.msvc.products.invoice.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, UUID>{
    Page<Product> findAll(Pageable pageable);
    Optional<Product> findByProductId(UUID productId);
    Optional<Product> deleteByProductId(UUID productId);
}
