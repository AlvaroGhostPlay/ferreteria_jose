package org.alvaro.ferreteria.jose.msvc.products.invoice.controllers;

import jakarta.validation.Valid;
import org.alvaro.ferreteria.jose.msvc.products.invoice.dto.request.InvoiceRequest;
import org.alvaro.ferreteria.jose.msvc.products.invoice.dto.response.InvoiceDTO;
import org.alvaro.ferreteria.jose.msvc.products.invoice.dto.response.InvoiceResponse;
import org.alvaro.ferreteria.jose.msvc.products.invoice.services.invoice.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;

import java.util.UUID;

@RequestMapping("/invoice")
@RestController
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @GetMapping("/{id}")
    public Mono<ResponseEntity<InvoiceResponse>> getInvoice(@PathVariable UUID id) {
        return invoiceService.getInvoice(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping("/empolyee/{id}")
    public Flux<?> getInvoicesByEmpolyee(@PathVariable UUID id) {
        return invoiceService.getHistorialByEmployee(id);
    }

    // OJO: GET + RequestBody no es buena práctica. Mejor usa @RequestParam o PathVariable.
    // Te lo dejo como: /invoice/empolyee-page/{id}/{page}
    @GetMapping("/empolyee-page/{id}/{page}")
    public Mono<ResponseEntity<Page<InvoiceDTO>>> getInvoicesByEmpolyeePage(@PathVariable UUID id, @PathVariable int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return invoiceService.getHistorialByEmployee(pageable, id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.noContent().build());
    }

    @GetMapping("/page/{page}")
    public Mono<ResponseEntity<Page<InvoiceDTO>>> getInvoicesByAdmin(@PathVariable int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return invoiceService.getHistorialByAdmin(pageable)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.noContent().build());
    }

    @PostMapping
    public Mono<ResponseEntity<InvoiceResponse>> saveInvoice(@Valid @RequestBody InvoiceRequest invoice) {
        return invoiceService.saveInvoice(invoice)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}