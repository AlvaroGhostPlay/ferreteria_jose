package org.alvaro.ferreteria.jose.msvc.products.invoice.repositories;

import java.util.Optional;
import java.util.UUID;

import org.alvaro.ferreteria.jose.msvc.products.invoice.entities.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface InvoiceRepository extends JpaRepository<Invoice, UUID>{
    Optional<Invoice> findByInvoiceId(UUID invoiceId);

    @Query("""
    SELECT i
    FROM Invoice i
    LEFT JOIN FETCH i.details
    WHERE i.invoiceId = :invoiceId
""")
Optional<Invoice> findInvoiceWithDetails(UUID invoiceId);
    
}
