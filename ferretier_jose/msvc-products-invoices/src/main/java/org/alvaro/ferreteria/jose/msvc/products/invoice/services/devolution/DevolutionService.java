package org.alvaro.ferreteria.jose.msvc.products.invoice.services.devolution;

import org.alvaro.ferreteria.jose.msvc.products.invoice.dto.request.CreateDevolutionRequest;
import org.alvaro.ferreteria.jose.msvc.products.invoice.dto.response.DevolutionDTO;
import org.alvaro.ferreteria.jose.msvc.products.invoice.dto.response.DevolutionResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface DevolutionService {
    Flux<DevolutionDTO> getDevolutionsListByEmployee(UUID employeeId);
    Mono<Page<DevolutionDTO>> getDevolutionsPageByEmployee(Pageable pageable, UUID employeeId);
    Mono<Page<DevolutionDTO>> getDevolutionsPage(Pageable pageable);

    // Nota: vos devolvías List<DevolutionResponse>. Mejor Flux<DevolutionResponse>.
    Flux<DevolutionResponse> getDevolutionById(UUID id);
    Flux<DevolutionResponse> getDevolutionByNumberInvoice(String number);

    Mono<DevolutionResponse> createDevolution(CreateDevolutionRequest req);
}