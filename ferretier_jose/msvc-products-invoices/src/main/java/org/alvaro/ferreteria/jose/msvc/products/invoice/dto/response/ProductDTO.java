package org.alvaro.ferreteria.jose.msvc.products.invoice.dto.response;

import java.time.LocalDate;
import java.util.UUID;

public record ProductDTO(
    UUID productId,
    String productName,
    LocalDate expirationDate,
    String descriptionProduct,
    String image,
    String code,
    Category category,
    Brand brand,
    Double price,
    Double iva,
    Integer stock
) {
    
}
