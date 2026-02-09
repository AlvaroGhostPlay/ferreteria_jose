package com.alvaro.springcloud.msvc.catalog.services.document_type;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.alvaro.springcloud.msvc.catalog.entities.DocumentType;

public interface DocumentTypeService {
    List<DocumentType> getAllDocumentType();
    Page<DocumentType> getAllDocumentTypePage(Pageable pageable);
    Optional<DocumentType> getDocumentTypeById(String id);
    Optional<DocumentType> save(DocumentType request);
    Optional<DocumentType> update(DocumentType request, String id);
    Optional<DocumentType> delete(String id);
    
}
