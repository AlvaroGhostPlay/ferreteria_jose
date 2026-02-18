package org.alvaro.ferreteria.jose.msvc.products.invoice.services.devolution;

import org.alvaro.ferreteria.jose.msvc.products.invoice.dto.request.CreateDevolutionRequest;
import org.alvaro.ferreteria.jose.msvc.products.invoice.dto.response.DevolutionDTO;
import org.alvaro.ferreteria.jose.msvc.products.invoice.dto.response.DevolutionResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DevolutionService {

    List<DevolutionDTO> getDevolutionsListByEmployee(UUID employeeId);
    Page<DevolutionDTO> getDevolutionsPageByEmployee(Pageable pageable, UUID employeeId);
    Page<DevolutionDTO> getDevolutionsPage(Pageable pageable);
    List<DevolutionResponse> getDevolutionById(UUID id);
    List<DevolutionResponse> getDevolutionByNumberInvoice(String id);
    Optional<DevolutionResponse> createDevolution(CreateDevolutionRequest req);
}
