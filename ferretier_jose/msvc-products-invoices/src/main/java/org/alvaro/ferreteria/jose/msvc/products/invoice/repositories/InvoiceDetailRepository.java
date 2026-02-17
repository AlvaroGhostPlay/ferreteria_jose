package org.alvaro.ferreteria.jose.msvc.products.invoice.repositories;

import java.util.List;
import java.util.UUID;

import org.alvaro.ferreteria.jose.msvc.products.invoice.entities.InvoiceDetail;
import org.alvaro.ferreteria.jose.msvc.products.invoice.keys.InvoiceDetailId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface InvoiceDetailRepository extends JpaRepository<InvoiceDetail, InvoiceDetailId>{

    @Query("""
            Select id from InvoiceDetail as id
            where id.id.invoiceId = ?1 
            """)
    List<InvoiceDetail> findByInvoiceid(UUID id);
}
