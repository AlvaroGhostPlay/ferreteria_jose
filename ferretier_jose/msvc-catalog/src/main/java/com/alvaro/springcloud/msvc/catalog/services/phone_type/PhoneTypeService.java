package com.alvaro.springcloud.msvc.catalog.services.phone_type;

import com.alvaro.springcloud.msvc.catalog.entities.PhoneType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface PhoneTypeService {
    List<PhoneType> getAllPhonesTypes();
    Page<PhoneType> getAllPhonesTypesPage(Pageable pageable);
    Optional<PhoneType> getPhonesById(String id);
    Optional<PhoneType> save(PhoneType request);
    Optional<PhoneType> update(PhoneType request, String id);
    Optional<PhoneType> delete(String id);
}
