package com.alvaro.springcloud.msvc.catalog.services.document_type;

import java.util.List;
import java.util.Optional;
    
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
    
import com.alvaro.springcloud.msvc.catalog.entities.DocumentType;
import com.alvaro.springcloud.msvc.catalog.repositories.DocumentTypeRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DocumentTypeServieImpl implements DocumentTypeService{
    
    @Autowired
    private DocumentTypeRepository documentTypeRepository;

    @Transactional(readOnly = true)
    @Override
    public Optional<DocumentType> getDocumentTypeById(String id) {
        return documentTypeRepository.findByDocumentTypeId(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<DocumentType> getAllDocumentType() {
        return documentTypeRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Page<DocumentType> getAllDocumentTypePage(Pageable pageable) {
        return documentTypeRepository.findAll(pageable);
    }

    @Transactional
    @Override
    public Optional<DocumentType> save(DocumentType request) {
        DocumentType documentType = new DocumentType();
        documentType.setDocumentType(request.getDocumentType());
        documentType.setDocumentTypeId(request.getDocumentTypeId());
        documentTypeRepository.save(documentType);
        return Optional.of(documentType);
    }

    @Transactional
    @Override
    public Optional<DocumentType> update(DocumentType request, String id) {
        Optional<DocumentType> documentType = documentTypeRepository.findByDocumentTypeId(id);
        if (documentType.isPresent()) {
            documentType.get().setDocumentType(request.getDocumentType());
            documentType.get().setDocumentTypeId(request.getDocumentTypeId());
            return documentType;
        }
        return Optional.empty();
    }   

    @Transactional
    @Override
    public Optional<DocumentType> delete(String id) {
        Optional<DocumentType> addOptional = documentTypeRepository.findByDocumentTypeId(id);
        if (addOptional.isPresent()) {
            return documentTypeRepository.deleteByDocumentTypeId(id);
        }
        return Optional.empty();
    }
    
}