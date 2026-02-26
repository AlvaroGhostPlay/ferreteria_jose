package org.alvaro.ferreteria.jose.msvc.products.invoice.services.devolution;

import org.alvaro.ferreteria.jose.msvc.products.invoice.dto.request.CreateDevolutionRequest;
import org.alvaro.ferreteria.jose.msvc.products.invoice.dto.response.*;
import org.alvaro.ferreteria.jose.msvc.products.invoice.entities.Devolution;
import org.alvaro.ferreteria.jose.msvc.products.invoice.entities.DevolutionDetail;
import org.alvaro.ferreteria.jose.msvc.products.invoice.entities.Product;
import org.alvaro.ferreteria.jose.msvc.products.invoice.keys.DevolutionDetailId;
import org.alvaro.ferreteria.jose.msvc.products.invoice.repositories.DevolutionRepository;
import org.alvaro.ferreteria.jose.msvc.products.invoice.repositories.ProductRepository;
import org.alvaro.ferreteria.jose.msvc.products.invoice.services.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.UUID;

@Service
public class DevolutionServiceImpl implements DevolutionService {

    @Autowired
    private DevolutionRepository devolutionRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private Mapper mapper;

    @Transactional(readOnly = true)
    @Override
    public Flux<DevolutionDTO> getDevolutionsListByEmployee(UUID employeeId) {
        return Mono.fromCallable(() -> devolutionRepository.findByIdEmployeeOrderByDateAsc(employeeId))
                .subscribeOn(Schedulers.boundedElastic())
                .flatMapMany(list -> Flux.fromIterable(list).map(this::convertDevolutionToDevolutionDTO));
    }

    @Transactional(readOnly = true)
    @Override
    public Mono<Page<DevolutionDTO>> getDevolutionsPageByEmployee(Pageable pageable, UUID employeeId) {
        return Mono.fromCallable(() -> devolutionRepository.findByIdEmployeeOrderByDateAsc(employeeId, pageable))
                .subscribeOn(Schedulers.boundedElastic())
                .map(page -> page.map(this::convertDevolutionToDevolutionDTO));
    }

    @Transactional(readOnly = true)
    @Override
    public Mono<Page<DevolutionDTO>> getDevolutionsPage(Pageable pageable) {
        return Mono.fromCallable(() -> devolutionRepository.findAllByOrderByDateAsc(pageable))
                .subscribeOn(Schedulers.boundedElastic())
                .map(page -> page.map(this::convertDevolutionToDevolutionDTO));
    }

    @Transactional(readOnly = true)
    @Override
    public Flux<DevolutionResponse> getDevolutionById(UUID id) {
        return Mono.fromCallable(() -> devolutionRepository.findByDevolutionIdOrderByDateAsc(id))
                .subscribeOn(Schedulers.boundedElastic())
                .flatMapMany(Flux::fromIterable)
                .flatMap(this::toDevolutionResponse);
    }

    @Transactional(readOnly = true)
    @Override
    public Flux<DevolutionResponse> getDevolutionByNumberInvoice(String number) {
        return Mono.fromCallable(() -> devolutionRepository.findByDevolutionNumberOrderByDateAsc(number))
                .subscribeOn(Schedulers.boundedElastic())
                .flatMapMany(Flux::fromIterable)
                .flatMap(this::toDevolutionResponse);
    }

    @Transactional
    @Override
    public Mono<DevolutionResponse> createDevolution(CreateDevolutionRequest req) {
        return Mono.fromCallable(() -> {
                    Devolution dev = new Devolution();
                    dev.setDate(req.devolution().getDate());
                    dev.setInvoiceNumber(req.devolution().getInvoiceNumber());
                    dev.setIdEmployee(req.devolution().getIdEmployee());
                    dev.setReason(req.devolution().getReason());
                    dev.setDevolutionNumber(req.devolution().getDevolutionNumber());

                    int correlative = 1;
                    for (var line : req.details()) {
                        DevolutionDetail det = new DevolutionDetail();

                        DevolutionDetailId id = new DevolutionDetailId();
                        id.setCorrelative(correlative++);
                        det.setId(id);

                        det.setQuantity(line.getQuantity());
                        det.setIva(line.getIva());
                        det.setSubTotal(line.getSubTotal());
                        det.setTotal(line.getTotal());

                        Product productRef = productRepository.getReferenceById(line.getProduct().getProductId());
                        det.setProduct(productRef);

                        dev.addDetail(det);
                    }

                    return devolutionRepository.save(dev);
                })
                .subscribeOn(Schedulers.boundedElastic())
                .flatMap(this::toDevolutionResponseSingle);
    }

    // ---------- Helpers reactivos ----------

    private Mono<DevolutionResponse> toDevolutionResponse(Devolution devolution) {
        DevolutionDTO devolutionDTO = convertDevolutionToDevolutionDTO(devolution);

        return Flux.fromIterable(devolution.getDetails())
                .flatMap(this::convertDevolutionToDevolutionDetailDto) // ahora Mono<DevolutionDetailDTO>
                .collectList()
                .map(details -> new DevolutionResponse(devolutionDTO, details));
    }

    private Mono<DevolutionResponse> toDevolutionResponseSingle(Devolution devolution) {
        return toDevolutionResponse(devolution);
    }

    private DevolutionDTO convertDevolutionToDevolutionDTO(Devolution dev) {
        return new DevolutionDTO(
                dev.getDevolutionId(),
                dev.getInvoiceNumber(),
                dev.getIdEmployee(),
                dev.getReason(),
                dev.getState(),
                dev.getDevolutionNumber(),
                dev.getDate()
        );
    }

    private Mono<DevolutionDetailDTO> convertDevolutionToDevolutionDetailDto(DevolutionDetail dev) {
        return mapper.createProductDTO(dev.getProduct())
                .map(productDTO -> new DevolutionDetailDTO(
                        dev.getIva(),
                        dev.getSubTotal(),
                        dev.getTotal(),
                        dev.getQuantity(),
                        productDTO
                ));
    }
}