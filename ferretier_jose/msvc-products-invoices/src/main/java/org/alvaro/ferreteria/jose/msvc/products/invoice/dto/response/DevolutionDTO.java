package org.alvaro.ferreteria.jose.msvc.products.invoice.dto.response;

import java.util.Date;
import java.util.UUID;

public record DevolutionDTO(
    UUID devolutionId,
    String invoiceNumber,
    UUID idEmployee,
    String reason,
    Boolean state,
    String devolutionNumber,
    Date date
) {
}
