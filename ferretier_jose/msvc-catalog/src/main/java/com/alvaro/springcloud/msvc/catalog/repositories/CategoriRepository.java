package com.alvaro.springcloud.msvc.catalog.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.alvaro.springcloud.msvc.catalog.entities.Category;


public interface CategoriRepository extends JpaRepository<Category, String>{
    Optional<Category> findByCategoryId(String id);
    Optional<Category> deleteByCategoryId(String id);
    Page<Category> findAll(Pageable pageable);
    
}
