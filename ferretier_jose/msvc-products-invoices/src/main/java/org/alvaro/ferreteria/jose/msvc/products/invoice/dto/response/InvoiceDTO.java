package org.alvaro.ferreteria.jose.msvc.products.invoice.dto.response;

import java.util.UUID;

public record InvoiceDTO(
    UUID invoiceId,
    UUID idClient,
    UUID idEmplyee,
    Double subTotal,
    Double iva,
    Double total,
    Boolean state
) {
    
}
