package com.alvaro.springcloud.msvc.catalog.repositories;

import java.util.Optional;

import org.springframework.boot.data.autoconfigure.web.DataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import com.alvaro.springcloud.msvc.catalog.entities.AddressType;

public interface AddressTypeRepository extends JpaRepository<AddressType, String>{
    Optional<AddressType> findByAddressTypeId(String id);
    Optional<AddressType> deleteByAddressTypeId(String id);
    Page<AddressType> findAll(Pageable pageable);
    
}
