package org.alvaro.ferreteria.jose.msvc.products.invoice.dto.request;

import jakarta.validation.Valid;
import org.alvaro.ferreteria.jose.msvc.products.invoice.entities.Devolution;
import org.alvaro.ferreteria.jose.msvc.products.invoice.entities.DevolutionDetail;

import java.util.List;

public record CreateDevolutionRequest(
        @Valid Devolution devolution,
        @Valid List<DevolutionDetail> details
        ) {
}
