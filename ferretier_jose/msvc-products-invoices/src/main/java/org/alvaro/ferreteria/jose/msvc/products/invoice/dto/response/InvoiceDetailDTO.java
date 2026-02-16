package org.alvaro.ferreteria.jose.msvc.products.invoice.dto.response;

import java.math.BigDecimal;
import java.util.UUID;

public record InvoiceDetailDTO(
    UUID idInvoice,
    Integer correl,
    ProductDTO product,
    BigDecimal iva,
    BigDecimal subTotal,
    BigDecimal total,
    Integer quantity
) {

}
