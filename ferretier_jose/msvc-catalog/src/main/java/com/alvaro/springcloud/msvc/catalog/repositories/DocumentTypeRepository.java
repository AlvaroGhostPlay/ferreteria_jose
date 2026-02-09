package com.alvaro.springcloud.msvc.catalog.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alvaro.springcloud.msvc.catalog.entities.DocumentType;

public interface DocumentTypeRepository extends JpaRepository<DocumentType, String>{
    Optional<DocumentType> findByDocumentTypeId(String id);
    Optional<DocumentType> deleteByDocumentTypeId(String id);
    
}
