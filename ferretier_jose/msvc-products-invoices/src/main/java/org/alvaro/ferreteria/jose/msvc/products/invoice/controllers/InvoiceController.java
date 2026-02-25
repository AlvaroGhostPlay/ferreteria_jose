package org.alvaro.ferreteria.jose.msvc.products.invoice.controllers;

import jakarta.validation.Valid;
import org.alvaro.ferreteria.jose.msvc.products.invoice.dto.request.InvoiceRequest;
import org.alvaro.ferreteria.jose.msvc.products.invoice.dto.response.InvoiceResponse;
import org.alvaro.ferreteria.jose.msvc.products.invoice.services.invoice.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RequestMapping("/invoice")
@RestController
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getInvoice(@PathVariable UUID id) {
        return ResponseEntity.ok().body(invoiceService.getInvoice(id));
    }

    @GetMapping("/empolyee/{id}")
    public ResponseEntity<?> getInvoicesByEmpolyee(@PathVariable UUID id) {
        return ResponseEntity.ok().body(invoiceService.getHistorialByEmployee(id));
    }

    @GetMapping("/empolyee-page/{page}")
    public ResponseEntity<?> getInvoicesByEmpolyee(@PathVariable int page, @RequestBody UUID id) {
        Pageable pageable = PageRequest.of(page, 10);
        return ResponseEntity.ok().body(invoiceService.getHistorialByEmployee(pageable, id));
    }

    @GetMapping("/page/{page}")
    public ResponseEntity<?> getInvoicesByEmpolyee(@PathVariable int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return ResponseEntity.ok().body(invoiceService.getHistorialByAdmin(pageable));
    }

    @PostMapping
    public ResponseEntity<?> saveInvoice(@Valid @RequestBody InvoiceRequest invoice) {
        Optional<InvoiceResponse> invoiceOptional = invoiceService.saveInvoice(invoice);
        return invoiceOptional.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
