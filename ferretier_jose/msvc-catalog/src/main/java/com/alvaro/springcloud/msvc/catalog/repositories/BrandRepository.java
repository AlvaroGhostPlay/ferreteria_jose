package com.alvaro.springcloud.msvc.catalog.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.alvaro.springcloud.msvc.catalog.entities.Brand;

public interface BrandRepository extends JpaRepository<Brand, String>{
    Optional<Brand> findByBrandId(String id);
    Optional<Brand> deleteByBrandId(String id);
    Page<Brand> findAll(Pageable pageable);
    
}
