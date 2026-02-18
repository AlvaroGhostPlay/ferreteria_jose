package org.alvaro.ferreteria.jose.msvc.products.invoice.dto.response;

import java.util.List;

public record DevolutionResponse(
        DevolutionDTO devolution,
        List <DevolutionDetailDTO> detalis
) {
}
