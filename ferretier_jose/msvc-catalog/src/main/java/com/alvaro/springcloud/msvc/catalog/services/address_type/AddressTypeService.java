package com.alvaro.springcloud.msvc.catalog.services.address_type;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.alvaro.springcloud.msvc.catalog.entities.AddressType;

public interface AddressTypeService {   
    List<AddressType> getAllAddresType();
    Page<AddressType> getAllAddresTypePage(Pageable pageable);
    Optional<AddressType> getAddresTypeById(String id);
    Optional<AddressType> save(AddressType request);
    Optional<AddressType> update(AddressType request, String id);
    Optional<AddressType> delete(String id);
}