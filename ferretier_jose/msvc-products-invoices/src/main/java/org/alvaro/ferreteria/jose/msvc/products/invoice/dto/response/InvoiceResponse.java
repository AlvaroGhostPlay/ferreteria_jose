package org.alvaro.ferreteria.jose.msvc.products.invoice.dto.response;

import java.util.List;

public record InvoiceResponse(
    InvoiceDTO invoice,
    List<InvoiceDetailDTO> details
) {   
}
