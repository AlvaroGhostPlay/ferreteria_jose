package org.alvaro.ferreteria.jose.msvc.products.invoice.services;

import org.alvaro.ferreteria.jose.msvc.products.invoice.dto.response.BrandDTO;
import org.alvaro.ferreteria.jose.msvc.products.invoice.dto.response.CategoryDTO;
import org.alvaro.ferreteria.jose.msvc.products.invoice.dto.response.ProductDTO;
import org.alvaro.ferreteria.jose.msvc.products.invoice.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@Service
public class Mapper {

    @Autowired
    private WebClient.Builder clientBuilder;

    public Mono<ProductDTO> createProductDTO(Product product) {
        Mono<BrandDTO> brandMono = getCatalog("/brand", product.getIdBrand(), BrandDTO.class)
                .cast(BrandDTO.class)
                .switchIfEmpty(Mono.empty());

        Mono<CategoryDTO> categoryMono = getCatalog("/category", product.getIdCategory(), CategoryDTO.class)
                .cast(CategoryDTO.class)
                .switchIfEmpty(Mono.empty());

        return Mono.zip(
                categoryMono.defaultIfEmpty(new CategoryDTO(null, null)), // <-- NO null
                brandMono.defaultIfEmpty(new BrandDTO(null, null))        // <-- NO null
        ).map(tuple -> {
            CategoryDTO category = tuple.getT1();
            BrandDTO brand = tuple.getT2();
            return new ProductDTO(
                    product.getProductId(),
                    product.getProductName(),
                    product.getExpirationDate(),
                    product.getDescriptionProduct(),
                    product.getImage(),
                    product.getCode(),
                    category,
                    brand,
                    product.getPrice(),
                    product.getIva(),
                    product.getStock()
            );
        });
    }

    private <T> Mono<T> getCatalog(String uri, Object id, Class<T> responseType) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);

        return clientBuilder.build()
                .get()
                .uri("http://msvc-catalog" + uri + "/{id}", params)
                .retrieve()
                .bodyToMono(responseType)
                .onErrorResume(e -> Mono.empty());
    }
}
