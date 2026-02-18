package org.alvaro.ferreteria.jose.msvc.products.invoice.dto.response;

public record InvoiceDevolutionResponse(
        InvoiceResponse invoice,
        DevolutionResponse devolution
) {
}
