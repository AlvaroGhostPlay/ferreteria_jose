package com.alvaro.springcloud.msvc.catalog.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.alvaro.springcloud.msvc.catalog.entities.MethodPaymment;

public interface MethodPaymmetRepository extends JpaRepository<MethodPaymment, String>{
    Optional<MethodPaymment> findByMethodPaymmentId(String id);
    Optional<MethodPaymment> deleteByMethodPaymmentId(String id);
    Page<MethodPaymment> findAll(Pageable pageable);
    
}
