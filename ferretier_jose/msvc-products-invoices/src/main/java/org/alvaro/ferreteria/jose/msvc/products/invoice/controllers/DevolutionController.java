package org.alvaro.ferreteria.jose.msvc.products.invoice.controllers;

import jakarta.validation.Valid;
import org.alvaro.ferreteria.jose.msvc.products.invoice.dto.request.CreateDevolutionRequest;
import org.alvaro.ferreteria.jose.msvc.products.invoice.dto.response.DevolutionResponse;
import org.alvaro.ferreteria.jose.msvc.products.invoice.services.devolution.DevolutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RequestMapping("/devolution")
@RestController
public class DevolutionController {

    @Autowired
    private DevolutionService devolutionService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getInvoice(@PathVariable UUID id) {
        return ResponseEntity.ok().body(devolutionService.getDevolutionById(id));
    }

    @GetMapping("/empolyee/{id}")
    public ResponseEntity<?> getInvoicesByEmpolyee(@PathVariable UUID id) {
        return ResponseEntity.ok().body(devolutionService.getDevolutionsListByEmployee(id));
    }

    @GetMapping("/empolyee-page/{page}")
    public ResponseEntity<?> getInvoicesByEmpolyee(@PathVariable int page, @RequestBody UUID id) {
        Pageable pageable = PageRequest.of(page, 10);
        return ResponseEntity.ok().body(devolutionService.getDevolutionsPageByEmployee(pageable, id));
    }

    @GetMapping("/page/{page}")
    public ResponseEntity<?> getInvoicesByEmpolyee(@PathVariable int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return ResponseEntity.ok().body(devolutionService.getDevolutionsPage(pageable));
    }

    @GetMapping("/number/{number}")
    public ResponseEntity<?> getInvoicesByInvoiceNumber(@PathVariable String number) {
        return ResponseEntity.ok().body(devolutionService.getDevolutionByNumberInvoice(number));
    }

    @PostMapping
    public ResponseEntity<?> saveInvoice(@Valid @RequestBody CreateDevolutionRequest invoice) {
        Optional<DevolutionResponse> invoiceOptional = devolutionService.createDevolution(invoice);
        return invoiceOptional.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
