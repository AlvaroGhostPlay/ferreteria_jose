package org.alvaro.ferreteria.jose.msvc.products.invoice.services.invoice;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.alvaro.ferreteria.jose.msvc.products.invoice.dto.request.InvoiceRequest;
import org.alvaro.ferreteria.jose.msvc.products.invoice.dto.response.InvoiceDTO;
import org.alvaro.ferreteria.jose.msvc.products.invoice.dto.response.InvoiceResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface InvoiceService {
    List<InvoiceDTO> getHistorialByEmployee(UUID idEmployeee);
    Page<InvoiceDTO> getHistorialByEmployee(Pageable pageablem, UUID idEmployee);
    Page<InvoiceDTO> getHistorialByAdmin(Pageable pageable);
    Optional<InvoiceResponse> getInvoice(UUID idInvoice);
    Optional<InvoiceResponse> saveInvoice(InvoiceRequest requestInvoice);
}
