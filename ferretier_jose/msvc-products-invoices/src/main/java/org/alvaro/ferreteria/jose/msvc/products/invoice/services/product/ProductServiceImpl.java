package org.alvaro.ferreteria.jose.msvc.products.invoice.services.product;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.alvaro.ferreteria.jose.msvc.products.invoice.entities.Product;
import org.alvaro.ferreteria.jose.msvc.products.invoice.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired 
    private ProductRepository productRepository;

    @Override
    @Transactional(readOnly = true)
    public Page<Product> GetAllProductsPage(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Product> getProductById(UUID id) {
        Optional<Product> productDb = this.productRepository.findByProductId(id);
        return productDb;
    }

    @Override
    @Transactional
    public Optional<Product> saveProduct(Product requestProduct) {
        Product product = new Product();
        product.setProductName(requestProduct.getProductName());
        product.setDescriptionProduct(requestProduct.getDescriptionProduct());
        product.setIdCategory(requestProduct.getIdCategory());
        product.setPrice(requestProduct.getPrice());
        product.setIva(requestProduct.getIva());
        product.setStock(requestProduct.getStock());
        product.setIdBrand(requestProduct.getIdBrand());
        product.setExpirationDate(requestProduct.getExpirationDate());
        product.setImage(requestProduct.getImage());
        return Optional.of(productRepository.save(product));
    }

    @Override
    @Transactional
    public Optional<Product> updateProduct(Product requestProduct, UUID id) {
        Optional<Product> prodOptional = productRepository.findByProductId(id);
        if (prodOptional.isPresent()) {
            Product product = prodOptional.get();
            product.setProductName(requestProduct.getProductName());
            product.setDescriptionProduct(requestProduct.getDescriptionProduct());
            product.setIdCategory(requestProduct.getIdCategory());
            product.setPrice(requestProduct.getPrice());
            product.setIva(requestProduct.getIva());
            product.setStock(requestProduct.getStock());
            product.setIdBrand(requestProduct.getIdBrand());
            product.setExpirationDate(requestProduct.getExpirationDate());
            product.setImage(requestProduct.getImage());
            return Optional.of(productRepository.save(product));
        } 
        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<Product> deleteProductById(UUID id) {
        Optional<Product> prodOptional = productRepository.findByProductId(id);
        if (prodOptional.isPresent()) {
            return productRepository.deleteByProductId(id);
        } 
        return Optional.empty();
    }
    
}
