package org.alvaro.ferreteria.jose.msvc.products.invoice.dto.request;

import java.util.List;

import org.alvaro.ferreteria.jose.msvc.products.invoice.entities.Invoice;
import org.alvaro.ferreteria.jose.msvc.products.invoice.entities.InvoiceDetail;

import jakarta.validation.Valid;

public record InvoiceRequest(
        @Valid Invoice invoice,
        @Valid List<InvoiceDetail> details
) {}
