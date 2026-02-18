package org.alvaro.ferreteria.jose.msvc.products.invoice.repositories;

import org.alvaro.ferreteria.jose.msvc.products.invoice.entities.Devolution;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface DevolutionRepository extends JpaRepository<Devolution, UUID> {
    @Query("""
    SELECT d
    FROM Devolution d
    LEFT JOIN FETCH d.details
    WHERE d.devolutionNumber = :invoiceNumber
""")
    List<Devolution> findByDevolutionNumberOrderByDateAsc(String invoiceNumber);
    @Query("""
    SELECT d
    FROM Devolution d
    LEFT JOIN FETCH d.details
    WHERE d.devolutionId = :devolutionId
""")
    List<Devolution> findByDevolutionIdOrderByDateAsc(UUID devolutionId);
    Page<Devolution> findByIdEmployeeOrderByDateAsc(UUID idEmployee, Pageable pageable);
    Page<Devolution> findAllByOrderByDateAsc(Pageable pageable);
    List<Devolution> findByIdEmployeeOrderByDateAsc(UUID idEmployee);
}