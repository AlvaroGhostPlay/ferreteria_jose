package org.alvaro.ferreteria.jose.msvc.products.invoice.repositories;

import org.alvaro.ferreteria.jose.msvc.products.invoice.entities.DevolutionDetail;
import org.alvaro.ferreteria.jose.msvc.products.invoice.keys.DevolutionDetailId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DevolutionDetailRepository extends JpaRepository<DevolutionDetail, DevolutionDetailId> {

}
