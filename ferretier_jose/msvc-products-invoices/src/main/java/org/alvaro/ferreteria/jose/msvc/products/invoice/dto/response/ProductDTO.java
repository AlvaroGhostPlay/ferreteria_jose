package org.alvaro.ferreteria.jose.msvc.products.invoice.dto.response;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

public record ProductDTO(
    UUID productId,
    String productName,
    LocalDate expirationDate,
    String descriptionProduct
) {
    
}
