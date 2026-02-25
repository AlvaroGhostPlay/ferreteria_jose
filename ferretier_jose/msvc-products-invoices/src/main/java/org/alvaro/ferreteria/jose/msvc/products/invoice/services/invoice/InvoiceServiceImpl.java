package org.alvaro.ferreteria.jose.msvc.products.invoice.services.invoice;

import java.util.*;

import org.alvaro.ferreteria.jose.msvc.products.invoice.dto.request.InvoiceRequest;
import org.alvaro.ferreteria.jose.msvc.products.invoice.dto.response.Brand;
import org.alvaro.ferreteria.jose.msvc.products.invoice.dto.response.InvoiceDTO;
import org.alvaro.ferreteria.jose.msvc.products.invoice.dto.response.InvoiceDetailDTO;
import org.alvaro.ferreteria.jose.msvc.products.invoice.dto.response.InvoiceResponse;
import org.alvaro.ferreteria.jose.msvc.products.invoice.dto.response.ProductDTO;
import org.alvaro.ferreteria.jose.msvc.products.invoice.entities.Invoice;
import org.alvaro.ferreteria.jose.msvc.products.invoice.entities.InvoiceDetail;
import org.alvaro.ferreteria.jose.msvc.products.invoice.keys.InvoiceDetailId;
import org.alvaro.ferreteria.jose.msvc.products.invoice.entities.Product;
import org.alvaro.ferreteria.jose.msvc.products.invoice.repositories.InvoiceDetailRepository;
import org.alvaro.ferreteria.jose.msvc.products.invoice.repositories.InvoiceRepository;
import org.alvaro.ferreteria.jose.msvc.products.invoice.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.core.MediaType;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private InvoiceDetailRepository invoiceDetailRepository;

    @Autowired
    private WebClient.Builder clientBuilder;

    @Override
    @Transactional(readOnly = true)
    public Optional<InvoiceResponse> getInvoice(UUID idInvoice) {
        Optional<Invoice> invoice = this.invoiceRepository.findInvoiceWithDetails(idInvoice);
        if (invoice.isPresent()) {
            InvoiceDTO invoiceDTO = createInvoiceDTO(invoice.get());
            List<InvoiceDetailDTO> invoiceDetails = invoice.get().getDetails()
                    .stream()
                    .map(this::createInvoiceDetailDTO)
                    .toList();
            return Optional.of(new InvoiceResponse(invoiceDTO, invoiceDetails));
        }
        return Optional.empty();
    }

    @Override
    @Transactional(readOnly = true)
    public List<InvoiceDTO> getHistorialByEmployee(UUID idEmployeee) {
        List<Invoice> invoicesPage = this.invoiceRepository.findAllInvoiceByEmployeeList(idEmployeee);
        return invoicesPage.stream().map(this::createInvoiceDTO).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<InvoiceDTO> getHistorialByEmployee(Pageable pageable, UUID idEmployee) {
        Page<Invoice> invoicesPage = this.invoiceRepository.findByIdEmplyeeOrderByDateAsc(idEmployee, pageable);
        return invoicesPage.map(this::createInvoiceDTO);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<InvoiceDTO> getHistorialByAdmin(Pageable pageable) {
        Page<Invoice> invoicesPage = this.invoiceRepository.findAllByOrderByDateAsc(pageable);
        return invoicesPage.map(this::createInvoiceDTO);
    }

    @Override
    @Transactional
    public Optional<InvoiceResponse> saveInvoice(InvoiceRequest requestInvoice) {
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

            // (a) ID compuesto (si lo usas)
            detail.setId(id);

            // (b) Campos
            detail.setQuantity(d.getQuantity());
            detail.setIva(d.getIva());
            detail.setSubTotal(d.getSubTotal());
            detail.setTotal(d.getTotal());

            // (c) Producto: si solo tenés el UUID, usa referencia (no hace SELECT)
            Product productRef = productRepository.getReferenceById(d.getProduct().getProductId());
            detail.setProduct(productRef);

            // (d) Asociar detalle a la factura (esto setea detail.invoice)
            invoice.addDetail(detail);
        }

        // 3) Guardar: por cascade se guardan invoice + details
        Invoice saved = invoiceRepository.save(invoice);
        List<InvoiceDetail> detalisSave = saved.getDetails();

        List<InvoiceDetailDTO> details = detalisSave.stream().map(this::createInvoiceDetailDTO).toList();

        // 4) Mapear respuesta
        return Optional.of(new InvoiceResponse(createInvoiceDTO(saved), details));
    }

    private InvoiceDetailDTO createInvoiceDetailDTO(InvoiceDetail detail) {
        ProductDTO productDTO = createProductDTO(detail.getProduct());
        return new InvoiceDetailDTO(
                detail.getId().getInvoiceId(),
                detail.getId().getCorrelative(),
                productDTO,
                detail.getSubTotal(),
                detail.getIva(),
                detail.getTotal(),
                detail.getQuantity());
    }

    private ProductDTO createProductDTO(Product product) {
        String uri = "/brand";

        Optional<?> document = getCatalog(uri, product.getIdBrand(), Brand.class);
        Brand brand = null;
        if (document.isPresent()) {
            brand = (Brand) document.get();
        }

        return new ProductDTO(
                product.getProductId(),
                product.getProductName(),
                product.getExpirationDate(),
                product.getDescriptionProduct()),
                brand
                ;
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
                invoice.getInvoiceNumber());
    }

    @NotNull
    private <T> Optional<T> getCatalog(String uri, Object id, Class<T>responseType) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);

        return clientBuilder.build()
                .get()
                .uri("http://msvc-catalog" + uri + "/{id}", params)
                .retrieve()
                .accept(MediaType.APPLICATION_JSON)
                .bodyToMono(responseType) // Se usa el parámetro Class<T>
                .blockOptional();
    }
}
