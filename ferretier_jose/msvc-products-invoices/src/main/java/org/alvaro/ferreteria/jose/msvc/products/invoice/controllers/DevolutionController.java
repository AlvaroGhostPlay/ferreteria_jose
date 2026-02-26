package org.alvaro.ferreteria.jose.msvc.products.invoice.controllers;

import jakarta.validation.Valid;
import org.alvaro.ferreteria.jose.msvc.products.invoice.dto.request.CreateDevolutionRequest;
import org.alvaro.ferreteria.jose.msvc.products.invoice.dto.response.DevolutionDTO;
import org.alvaro.ferreteria.jose.msvc.products.invoice.dto.response.DevolutionResponse;
import org.alvaro.ferreteria.jose.msvc.products.invoice.services.devolution.DevolutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RequestMapping("/devolution")
@RestController
public class DevolutionController {

    @Autowired
    private DevolutionService devolutionService;

    @GetMapping("/{id}")
    public Flux<?> getDevolutionById(@PathVariable UUID id) {
        return devolutionService.getDevolutionById(id);
    }

    @GetMapping("/empolyee/{id}")
    public Flux<?> getDevolutionsByEmpolyee(@PathVariable UUID id) {
        return devolutionService.getDevolutionsListByEmployee(id);
    }

    @GetMapping("/empolyee-page/{id}/{page}")
    public Mono<ResponseEntity<Page<DevolutionDTO>>> getDevolutionsPageByEmpolyee(@PathVariable UUID id, @PathVariable int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return devolutionService.getDevolutionsPageByEmployee(pageable, id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.noContent().build());
    }

    @GetMapping("/page/{page}")
    public Mono<ResponseEntity<Page<DevolutionDTO>>> getDevolutionsPage(@PathVariable int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return devolutionService.getDevolutionsPage(pageable)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.noContent().build());
    }

    @GetMapping("/number/{number}")
    public Flux<?> getDevolutionByInvoiceNumber(@PathVariable String number) {
        return devolutionService.getDevolutionByNumberInvoice(number);
    }

    @PostMapping
    public Mono<ResponseEntity<DevolutionResponse>> saveDevolution(@Valid @RequestBody CreateDevolutionRequest req) {
        return devolutionService.createDevolution(req)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}