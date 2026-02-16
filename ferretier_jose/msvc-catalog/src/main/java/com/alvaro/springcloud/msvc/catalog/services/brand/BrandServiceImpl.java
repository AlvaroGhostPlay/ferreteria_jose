package com.alvaro.springcloud.msvc.catalog.services.brand;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.alvaro.springcloud.msvc.catalog.entities.Brand;
import com.alvaro.springcloud.msvc.catalog.repositories.BrandRepository;

import org.springframework.transaction.annotation.Transactional;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandRepository brandRepository;

    @Transactional(readOnly = true)
    @Override
    public Optional<Brand> getBrandById(String id) {
        return brandRepository.findByBrandId(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Brand> getAllBrand() {
        return brandRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Brand> getAllBrandPage(Pageable pageable) {
        return brandRepository.findAll(pageable);
    }

    @Transactional
    @Override
    public Optional<Brand> save(Brand request) {
        Brand brand = new Brand();
        brand.setBrand(request.getBrand());
        brand.setBrandId(request.getBrandId());
        brandRepository.save(brand);
        return Optional.of(brand);
    }

    @Transactional
    @Override
    public Optional<Brand> update(Brand request, String id) {
        Optional<Brand> addOptional = brandRepository.findByBrandId(id);
        if (addOptional.isPresent()) {
            addOptional.get().setBrand(request.getBrand());
            addOptional.get().setBrandId(request.getBrandId());
            return addOptional;
        }
        return Optional.empty();
    }   

    @Transactional
    @Override
    public Optional<Brand> delete(String id) {
        Optional<Brand> addOptional = brandRepository.findByBrandId(id);
        if (addOptional.isPresent()) {
            return brandRepository.deleteByBrandId(id);
        }
        return Optional.empty();
    }
}
