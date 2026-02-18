package org.alvaro.ferreteria.jose.msvc.products.invoice.dto.response;

import jakarta.persistence.Column;

import java.math.BigDecimal;

public record DevolutionDetailDTO(
    BigDecimal iva,
    BigDecimal subTotal,
    BigDecimal total,
    Integer quantity,
    ProductDTO product
) {
}
