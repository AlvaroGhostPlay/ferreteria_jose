package org.alvaro.ferreteria.jose.msvc.products.invoice.services.invoice;

import org.alvaro.ferreteria.jose.msvc.products.invoice.dto.request.InvoiceRequest;
import org.alvaro.ferreteria.jose.msvc.products.invoice.dto.response.*;
import org.alvaro.ferreteria.jose.msvc.products.invoice.entities.Invoice;
import org.alvaro.ferreteria.jose.msvc.products.invoice.entities.InvoiceDetail;
import org.alvaro.ferreteria.jose.msvc.products.invoice.entities.Product;
import org.alvaro.ferreteria.jose.msvc.products.invoice.keys.InvoiceDetailId;
import org.alvaro.ferreteria.jose.msvc.products.invoice.repositories.InvoiceRepository;
import org.alvaro.ferreteria.jose.msvc.products.invoice.repositories.ProductRepository;
import org.alvaro.ferreteria.jose.msvc.products.invoice.services.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.UUID;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private Mapper mapper;

    @Override
    @Transactional(readOnly = true)
    public Mono<InvoiceResponse> getInvoice(UUID idInvoice) {
        return Mono.fromCallable(() -> invoiceRepository.findInvoiceWithDetails(idInvoice))
                .subscribeOn(Schedulers.boundedElastic())
                .flatMap(opt -> {
                    if (opt.isEmpty()) return Mono.empty();

                    Invoice invoice = opt.get();
                    InvoiceDTO invoiceDTO = createInvoiceDTO(invoice);

                    return Flux.fromIterable(invoice.getDetails())
                            .flatMap(this::createInvoiceDetailDTO) // ahora devuelve Mono<InvoiceDetailDTO>
                            .collectList()
                            .map(details -> new InvoiceResponse(invoiceDTO, details));
                });
    }

    @Override
    @Transactional(readOnly = true)
    public Flux<InvoiceDTO> getHistorialByEmployee(UUID idEmployeee) {
        return Mono.fromCallable(() -> invoiceRepository.findAllInvoiceByEmployeeList(idEmployeee))
                .subscribeOn(Schedulers.boundedElastic())
                .flatMapMany(list -> Flux.fromIterable(list).map(this::createInvoiceDTO));
    }

    @Override
    @Transactional(readOnly = true)
    public Mono<Page<InvoiceDTO>> getHistorialByEmployee(Pageable pageable, UUID idEmployee) {
        return Mono.fromCallable(() -> invoiceRepository.findByIdEmplyeeOrderByDateAsc(idEmployee, pageable))
                .subscribeOn(Schedulers.boundedElastic())
                .map(page -> page.map(this::createInvoiceDTO));
    }

    @Override
    @Transactional(readOnly = true)
    public Mono<Page<InvoiceDTO>> getHistorialByAdmin(Pageable pageable) {
        return Mono.fromCallable(() -> invoiceRepository.findAllByOrderByDateAsc(pageable))
                .subscribeOn(Schedulers.boundedElastic())
                .map(page -> page.map(this::createInvoiceDTO));
    }

    @Override
    @Transactional
    public Mono<InvoiceResponse> saveInvoice(InvoiceRequest requestInvoice) {
        return Mono.fromCallable(() -> {
                    int correlative = 1;

                    Invoice invoice = new Invoice();
                    invoice.setIva(requestInvoice.invoice().getIva());
                    invoice.setSubTotal(requestInvoice.invoice().getSubTotal());
                    invoice.setTotal(requestInvoice.invoice().getTotal());
                    invoice.setIdClient(requestInvoice.invoice().getIdClient());
                    invoice.setIdEmplyee(requestInvoice.invoice().getIdEmplyee());
                    invoice.setIdMethodPaymment(requestInvoice.invoice().getIdMethodPaymment());
                    invoice.setInvoiceNumber(requestInvoice.invoice().getInvoiceNumber());
                    invoice.setDate(requestInvoice.invoice().getDate());
                    invoice.setState(requestInvoice.invoice().getState());

                    for (var d : requestInvoice.details()) {

                        InvoiceDetailId id = new InvoiceDetailId();
                        id.setCorrelative(correlative++);

                        InvoiceDetail detail = new InvoiceDetail();
                        detail.setId(id);

                        detail.setQuantity(d.getQuantity());
                        detail.setIva(d.getIva());
                        detail.setSubTotal(d.getSubTotal());
                        detail.setTotal(d.getTotal());

                        Product productRef = productRepository.getReferenceById(d.getProduct().getProductId());
                        detail.setProduct(productRef);

                        invoice.addDetail(detail);
                    }

                    return invoiceRepository.save(invoice);
                })
                .subscribeOn(Schedulers.boundedElastic())
                .flatMap(saved -> {
                    InvoiceDTO invoiceDTO = createInvoiceDTO(saved);

                    return Flux.fromIterable(saved.getDetails())
                            .flatMap(this::createInvoiceDetailDTO)
                            .collectList()
                            .map(details -> new InvoiceResponse(invoiceDTO, details));
                });
    }

    private Mono<InvoiceDetailDTO> createInvoiceDetailDTO(InvoiceDetail detail) {
        return mapper.createProductDTO(detail.getProduct())
                .map(productDTO -> new InvoiceDetailDTO(
                        detail.getId().getInvoiceId(),
                        detail.getId().getCorrelative(),
                        productDTO,
                        detail.getSubTotal(),
                        detail.getIva(),
                        detail.getTotal(),
                        detail.getQuantity()
                ));
    }

    private InvoiceDTO createInvoiceDTO(Invoice invoice) {
        return new InvoiceDTO(
                invoice.getInvoiceId(),
                invoice.getIdClient(),
                invoice.getIdEmplyee(),
                invoice.getSubTotal(),
                invoice.getIva(),
                invoice.getTotal(),
                invoice.getState(),
                invoice.getDate(),
                invoice.getIdMethodPaymment(),
                invoice.getInvoiceNumber()
        );
    }
}