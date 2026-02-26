package org.alvaro.ferreteria.jose.msvc.products.invoice.services.invoice;

import org.alvaro.ferreteria.jose.msvc.products.invoice.dto.request.InvoiceRequest;
import org.alvaro.ferreteria.jose.msvc.products.invoice.dto.response.InvoiceDTO;
import org.alvaro.ferreteria.jose.msvc.products.invoice.dto.response.InvoiceResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface InvoiceService {
    Mono<InvoiceResponse> getInvoice(UUID idInvoice);
    Flux<InvoiceDTO> getHistorialByEmployee(UUID idEmployee);
    Mono<Page<InvoiceDTO>> getHistorialByEmployee(Pageable pageable, UUID idEmployee);
    Mono<Page<InvoiceDTO>> getHistorialByAdmin(Pageable pageable);
    Mono<InvoiceResponse> saveInvoice(InvoiceRequest requestInvoice);
}