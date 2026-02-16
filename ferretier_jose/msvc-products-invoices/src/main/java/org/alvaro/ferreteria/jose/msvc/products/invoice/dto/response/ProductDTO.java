package org.alvaro.ferreteria.jose.msvc.products.invoice.dto.response;

import java.util.Date;
import java.util.UUID;

public record ProductDTO(
    UUID productId,
    String productName,
    Date expirationDate,
    String descriptionProduct
) {
    
}
