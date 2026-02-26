package org.alvaro.ferreteria.jose.msvc.products.invoice.services.product;

import org.alvaro.ferreteria.jose.msvc.products.invoice.dto.response.ProductDTO;
import org.alvaro.ferreteria.jose.msvc.products.invoice.entities.Product;
import org.alvaro.ferreteria.jose.msvc.products.invoice.repositories.ProductRepository;
import org.alvaro.ferreteria.jose.msvc.products.invoice.services.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private Mapper mapper;

    public ProductServiceImpl(ProductRepository productRepository, Mapper mapper) {
        this.productRepository = productRepository;
        this.mapper = mapper;
    }

    @Override
    @Transactional(readOnly = true)
    public Mono<Page<ProductDTO>> GetAllProductsPage(Pageable pageable) {
        return Mono.fromCallable(() -> productRepository.findAll(pageable))
                .subscribeOn(Schedulers.boundedElastic()) // JPA bloquea -> mandalo a hilo elástico
                .flatMap(page -> Flux.fromIterable(page.getContent())
                        .flatMap(mapper::createProductDTO)
                        .collectList()
                        .map(list -> new PageImpl<>(list, pageable, page.getTotalElements()))
                );
    }

    @Override
    @Transactional(readOnly = true)
    public Flux<ProductDTO> getAllProducts() {
        return Mono.fromCallable(() -> productRepository.findAll())
                .subscribeOn(Schedulers.boundedElastic())
                .flatMapMany(list -> Flux.fromIterable(list).flatMap(mapper::createProductDTO));
    }

    @Override
    @Transactional(readOnly = true)
    public Mono<ProductDTO> getProductById(UUID id) {
        return Mono.fromCallable(() -> productRepository.findByProductId(id))
                .subscribeOn(Schedulers.boundedElastic())
                .flatMap(opt -> opt.map(mapper::createProductDTO).orElseGet(Mono::empty));
    }

    @Override
    @Transactional
    public Mono<ProductDTO> saveProduct(Product requestProduct) {
        return Mono.fromCallable(() -> {
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
                    product.setCode(requestProduct.getCode());
                    return productRepository.save(product);
                })
                .subscribeOn(Schedulers.boundedElastic())
                .flatMap(mapper::createProductDTO);
    }

    @Override
    @Transactional
    public Mono<ProductDTO> updateProduct(Product requestProduct, UUID id) {
        return Mono.fromCallable(() -> productRepository.findByProductId(id))
                .subscribeOn(Schedulers.boundedElastic())
                .flatMap(opt -> {
                    if (opt.isEmpty()) return Mono.empty();
                    Product product = opt.get();
                    product.setProductName(requestProduct.getProductName());
                    product.setDescriptionProduct(requestProduct.getDescriptionProduct());
                    product.setIdCategory(requestProduct.getIdCategory());
                    product.setPrice(requestProduct.getPrice());
                    product.setIva(requestProduct.getIva());
                    product.setStock(requestProduct.getStock());
                    product.setIdBrand(requestProduct.getIdBrand());
                    product.setExpirationDate(requestProduct.getExpirationDate());
                    product.setImage(requestProduct.getImage());
                    product.setCode(requestProduct.getCode());

                    return Mono.fromCallable(() -> productRepository.save(product))
                            .subscribeOn(Schedulers.boundedElastic())
                            .flatMap(mapper::createProductDTO);
                });
    }

    @Override
    @Transactional
    public Mono<ProductDTO> deleteProductById(UUID id) {
        return Mono.fromCallable(() -> productRepository.findByProductId(id))
                .subscribeOn(Schedulers.boundedElastic())
                .flatMap(opt -> {
                    if (opt.isEmpty()) return Mono.empty();

                    return Mono.fromCallable(() -> productRepository.deleteByProductId(id).orElse(null))
                            .subscribeOn(Schedulers.boundedElastic())
                            .flatMap(deleted -> deleted == null ? Mono.empty() : mapper.createProductDTO(deleted));
                });
    }
}