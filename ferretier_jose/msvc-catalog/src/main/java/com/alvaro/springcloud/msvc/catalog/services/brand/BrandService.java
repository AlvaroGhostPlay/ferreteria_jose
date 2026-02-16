package com.alvaro.springcloud.msvc.catalog.services.brand;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.alvaro.springcloud.msvc.catalog.entities.Brand;

public interface BrandService {   
    List<Brand> getAllBrand();
    Page<Brand> getAllBrandPage(Pageable pageable);
    Optional<Brand> getBrandById(String id);
    Optional<Brand> save(Brand request);
    Optional<Brand> update(Brand request, String id);
    Optional<Brand> delete(String id);
}