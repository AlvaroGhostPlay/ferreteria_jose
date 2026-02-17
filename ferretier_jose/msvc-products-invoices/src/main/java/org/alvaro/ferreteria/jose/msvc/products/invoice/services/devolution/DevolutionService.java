package org.alvaro.ferreteria.jose.msvc.products.invoice.services.devolution;

import org.alvaro.ferreteria.jose.msvc.products.invoice.dto.request.CreateDevolutionRequest;
import org.alvaro.ferreteria.jose.msvc.products.invoice.entities.Devolution;

public interface DevolutionService {

    Devolution createDevolution(CreateDevolutionRequest req);
}
