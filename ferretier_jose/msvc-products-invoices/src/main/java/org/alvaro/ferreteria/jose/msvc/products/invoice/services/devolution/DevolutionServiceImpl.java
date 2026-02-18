package org.alvaro.ferreteria.jose.msvc.products.invoice.services.devolution;

import org.alvaro.ferreteria.jose.msvc.products.invoice.dto.request.CreateDevolutionRequest;
import org.alvaro.ferreteria.jose.msvc.products.invoice.dto.response.DevolutionDTO;
import org.alvaro.ferreteria.jose.msvc.products.invoice.dto.response.DevolutionDetailDTO;
import org.alvaro.ferreteria.jose.msvc.products.invoice.dto.response.DevolutionResponse;
import org.alvaro.ferreteria.jose.msvc.products.invoice.dto.response.ProductDTO;
import org.alvaro.ferreteria.jose.msvc.products.invoice.entities.Devolution;
import org.alvaro.ferreteria.jose.msvc.products.invoice.entities.DevolutionDetail;
import org.alvaro.ferreteria.jose.msvc.products.invoice.entities.Product;
import org.alvaro.ferreteria.jose.msvc.products.invoice.keys.DevolutionDetailId;
import org.alvaro.ferreteria.jose.msvc.products.invoice.repositories.DevolutionRepository;
import org.alvaro.ferreteria.jose.msvc.products.invoice.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DevolutionServiceImpl implements DevolutionService {

    @Autowired
    private DevolutionRepository devolutionRepository;

    @Autowired
    private ProductRepository productRepository;

    @Transactional(readOnly = true)
    @Override
    public List<DevolutionDTO> getDevolutionsListByEmployee(UUID employeeId) {
        return this.devolutionRepository.findByIdEmployeeOrderByDateAsc(employeeId)
                .stream()
                .map(this::convertDevolutionToDevolutionDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public Page<DevolutionDTO> getDevolutionsPageByEmployee(Pageable pageable, UUID employeeId) {
        return this.devolutionRepository.findByIdEmployeeOrderByDateAsc(employeeId, pageable)
                .map(this::convertDevolutionToDevolutionDTO);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<DevolutionDTO> getDevolutionsPage(Pageable pageable) {
        return this.devolutionRepository.findAllByOrderByDateAsc(pageable).map(this::convertDevolutionToDevolutionDTO);
    }

    @Transactional(readOnly = true)
    @Override
    public List<DevolutionResponse> getDevolutionById(UUID id) {
        List<Devolution> devolutions = this.devolutionRepository.findByDevolutionIdOrderByDateAsc(id);
        return devolutions.stream().map(devolution -> {
            DevolutionDTO devolutionDTO = convertDevolutionToDevolutionDTO(devolution);
            List<DevolutionDetailDTO> devolutionDetailDTOS = devolution.getDetails().stream().map(this::convertDevolutionToDevolutionDetailDto).toList();
            return new DevolutionResponse(devolutionDTO, devolutionDetailDTOS);
        }).toList();
    }

    @Transactional(readOnly = true)
    @Override
    public List<DevolutionResponse> getDevolutionByNumberInvoice(String id) {
        List<Devolution> devolutions = this.devolutionRepository.findByDevolutionNumberOrderByDateAsc(id);
        return devolutions.stream().map(devolution -> {
            DevolutionDTO devolutionDTO = convertDevolutionToDevolutionDTO(devolution);
            List<DevolutionDetailDTO> devolutionDetailDTOS = devolution.getDetails().stream().map(this::convertDevolutionToDevolutionDetailDto).toList();
            return new DevolutionResponse(devolutionDTO, devolutionDetailDTOS);
        }).toList();
    }

    @Transactional
    @Override
    public Optional<DevolutionResponse> createDevolution(CreateDevolutionRequest req) {
        // 1) Crear encabezado
        Devolution dev = new Devolution();
        dev.setDate(req.devolution().getDate());
        dev.setInvoiceNumber(req.devolution().getInvoiceNumber());
        dev.setIdEmployee(req.devolution().getIdEmployee());
        dev.setReason(req.devolution().getReason());
        dev.setDevolutionNumber(req.devolution().getDevolutionNumber()); // o generarlo

        // 2) Crear detalles
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

            dev.addDetail(det); // setea det.devolution y por MapsId llenará devolutionId en el id al persistir
        }

        Devolution devolution = devolutionRepository.save(dev);

        DevolutionDTO devolutionDTO = convertDevolutionToDevolutionDTO(devolution);
        List<DevolutionDetailDTO> devolutionDetailDTOS = devolution.getDetails().stream().map(this::convertDevolutionToDevolutionDetailDto).toList();

        return Optional.of(new DevolutionResponse(devolutionDTO, devolutionDetailDTOS));

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

    private DevolutionDetailDTO convertDevolutionToDevolutionDetailDto(DevolutionDetail dev) {
        ProductDTO productDTO = createProductDTO(dev.getProduct());
        return new DevolutionDetailDTO(
                dev.getIva(),
                dev.getSubTotal(),
                dev.getTotal(),
                dev.getQuantity(),
                productDTO
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
}
