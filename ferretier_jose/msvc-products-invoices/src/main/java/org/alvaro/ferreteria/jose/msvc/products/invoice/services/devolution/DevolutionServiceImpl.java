package org.alvaro.ferreteria.jose.msvc.products.invoice.services.devolution;

import org.alvaro.ferreteria.jose.msvc.products.invoice.dto.request.CreateDevolutionRequest;
import org.alvaro.ferreteria.jose.msvc.products.invoice.entities.Devolution;
import org.alvaro.ferreteria.jose.msvc.products.invoice.entities.DevolutionDetail;
import org.alvaro.ferreteria.jose.msvc.products.invoice.entities.Product;
import org.alvaro.ferreteria.jose.msvc.products.invoice.keys.DevolutionDetailId;
import org.alvaro.ferreteria.jose.msvc.products.invoice.repositories.DevolutionRepository;
import org.alvaro.ferreteria.jose.msvc.products.invoice.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DevolutionServiceImpl implements DevolutionService {

    @Autowired
    private DevolutionRepository devolutionRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Devolution createDevolution(CreateDevolutionRequest req) {
        // 1) Crear encabezado
        Devolution dev = new Devolution();
        dev.setInvoiceNumber(req.devolution().getInvoiceNumber());
        dev.setIdEmployee(req.devolution().getIdEmployee());
        dev.setReason(req.devolution().getReason());
        dev.setDevolutionNumber(req.devolution().getDevolutionNumber()); // o generarlo

        // 2) Crear detalles
        int correlative = 1;

        for (var line : req.devolution().getDetails()) {
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

        return devolutionRepository.save(dev);
    }
}
