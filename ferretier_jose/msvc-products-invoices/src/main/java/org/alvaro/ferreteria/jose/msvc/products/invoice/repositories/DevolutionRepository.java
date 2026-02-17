package org.alvaro.ferreteria.jose.msvc.products.invoice.repositories;

import org.alvaro.ferreteria.jose.msvc.products.invoice.entities.Devolution;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DevolutionRepository extends JpaRepository<Devolution, UUID> {
    Page<Devolution> findByInvoiceNumberOrderByDateAsc(String invoiceNumber, Pageable pageable);
}