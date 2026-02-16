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
    List<InvoiceDTO> gethistorialByEmployee(UUID idEmployeee);
    Page<InvoiceDTO> gethistorialByEmployee(Pageable idEmployeee);
    Optional<InvoiceResponse> getInvoice(UUID idInvoice);
    Optional<InvoiceResponse> saveInvoice(InvoiceRequest requestInvoice);
    Optional<InvoiceResponse> updateInvoice(UUID idInvoice, InvoiceRequest requestInvoice);
    Optional<InvoiceResponse> devolutionIvoice(UUID idInvoice, InvoiceRequest requestInvoiceDev);
}
