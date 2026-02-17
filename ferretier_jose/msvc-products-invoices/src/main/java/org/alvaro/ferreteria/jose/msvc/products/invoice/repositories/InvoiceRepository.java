package org.alvaro.ferreteria.jose.msvc.products.invoice.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.alvaro.ferreteria.jose.msvc.products.invoice.entities.Invoice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
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

    Page<Invoice> findByIdEmplyeeOrderByDateAsc(UUID employeeId, Pageable pageable);

    Page<Invoice> findAllByOrderByDateAsc(Pageable pageable);

    @Query("""
    SELECT i
    FROM Invoice i
    LEFT JOIN FETCH i.details
    WHERE i.idEmplyee = ?1 order by i.date asc
""")
    List<Invoice> findAllInvoiceByEmployeeList(UUID employeeId);

    @Query("""
    SELECT i
    FROM Invoice i
    LEFT JOIN FETCH i.details order by i.date asc
""")
    Optional<Invoice> findAllByIvoiceAdmin(UUID employeeId);
    
}
