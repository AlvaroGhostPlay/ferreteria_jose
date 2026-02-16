package com.alvaro.springcloud.msvc.catalog.controllers;

import com.alvaro.springcloud.msvc.catalog.entities.DocumentType;
import com.alvaro.springcloud.msvc.catalog.services.document_type.DocumentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/document-type")
public class DocumentTypeController {

    @Autowired
    private DocumentTypeService documentTypeService;

    @GetMapping("/page/{page}")
    public ResponseEntity<?> getAllDocumentTypePage(@PathVariable int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return ResponseEntity.ok().body(documentTypeService.getAllDocumentTypePage(pageable));
    }

    @GetMapping
    public ResponseEntity<?> getDocumentTypeAll() {
        return ResponseEntity.ok().body(documentTypeService.getAllDocumentType());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDocumentType(@PathVariable String id) {
        Optional<DocumentType> response = documentTypeService.getDocumentTypeById(id);
        return response.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody DocumentType request){
        Optional<DocumentType> response = documentTypeService.save(request);
        return response.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(201).build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody DocumentType request, @PathVariable String id){
        Optional<DocumentType> response = documentTypeService.update(request, id);
        return response.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id){
        Optional<DocumentType> response = documentTypeService.delete(id);
        return response.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}