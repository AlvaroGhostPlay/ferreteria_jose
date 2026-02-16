package org.alvaro.ferreteria.jose.msvc.products.invoice.services.invoice;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.alvaro.ferreteria.jose.msvc.products.invoice.dto.request.InvoiceRequest;
import org.alvaro.ferreteria.jose.msvc.products.invoice.dto.response.InvoiceDTO;
import org.alvaro.ferreteria.jose.msvc.products.invoice.dto.response.InvoiceDetailDTO;
import org.alvaro.ferreteria.jose.msvc.products.invoice.dto.response.InvoiceResponse;
import org.alvaro.ferreteria.jose.msvc.products.invoice.dto.response.ProductDTO;
import org.alvaro.ferreteria.jose.msvc.products.invoice.entities.Invoice;
import org.alvaro.ferreteria.jose.msvc.products.invoice.entities.InvoiceDetail;
import org.alvaro.ferreteria.jose.msvc.products.invoice.entities.Product;
import org.alvaro.ferreteria.jose.msvc.products.invoice.repositories.InvoiceDetailRepository;
import org.alvaro.ferreteria.jose.msvc.products.invoice.repositories.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InvoiceServiceImpl implements InvoiceService{

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private InvoiceDetailRepository invoiceDetailRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<InvoiceResponse> getInvoice(UUID idInvoice) {
        Optional<Invoice> invoice = this.invoiceRepository.findInvoiceWithDetails(idInvoice);
        if (invoice.isPresent()) {
            InvoiceDTO invoiceDTO = createInvoiceDTO(invoice.get());
            List<InvoiceDetailDTO> invoiceDetails = invoice.get().getDetails()
            .stream()
            .map(detail -> createInvoiceDetailDTO(detail))
            .toList();
            return Optional.of(new InvoiceResponse(invoiceDTO, invoiceDetails));
        }
        return Optional.empty();
    }

    @Override
    @Transactional(readOnly = true)
    public List<InvoiceDTO> gethistorialByEmployee(UUID idEmployeee) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<InvoiceDTO> gethistorialByEmployee(Pageable idEmployeee) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    @Transactional
    public Optional<InvoiceResponse> saveInvoice(InvoiceRequest requestInvoice) {
        // TODO Auto-generated method stub
        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<InvoiceResponse> updateInvoice(UUID idInvoice, InvoiceRequest requestInvoice) {
        // TODO Auto-generated method stub
        return Optional.empty();
    }
    
    @Override
    @Transactional
    public Optional<InvoiceResponse> devolutionIvoice(UUID idInvoice, InvoiceRequest requestInvoiceDev) {
        // TODO Auto-generated method stub
        return Optional.empty();
    }

    private InvoiceDetailDTO createInvoiceDetailDTO(InvoiceDetail detail){
        ProductDTO productDTO = createProductDTO(detail.getProduct());
        return new InvoiceDetailDTO(
                    detail.getId().getInvoiceId(),
                    detail.getId().getCorrelative(),
                    productDTO,
                    detail.getSubTotal(),
                    detail.getIva(),
                    detail.getTotal(),
                    detail.getQuantity()
                );
    }

    private ProductDTO createProductDTO(Product product){
        return new ProductDTO(
                    product.getProductId(),
                    product.getProductName(),
                    product.getExpirationDate(),
                    product.getDescriptionProduct()
                );
    }

        private InvoiceDTO createInvoiceDTO(Invoice invoice){
        return new InvoiceDTO(
                invoice.getInvoiceId(),
                invoice.getIdClient(),
                invoice.getIdEmplyee(),
                invoice.getSubTotal(),
                invoice.getIva(),
                invoice.getTotal(),
                invoice.getState()
        );
    }
}
