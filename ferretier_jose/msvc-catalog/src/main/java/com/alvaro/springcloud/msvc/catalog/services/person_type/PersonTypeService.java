package com.alvaro.springcloud.msvc.catalog.services.person_type;

import com.alvaro.springcloud.msvc.catalog.entities.PersonType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface PersonTypeService {
    List<PersonType> getAllPersonTypes();
    Page<PersonType> getAllPersonTypesPage(Pageable pageable);
    Optional<PersonType> getPersonById(String id);
    Optional<PersonType> save(PersonType request);
    Optional<PersonType> update(PersonType request, String id);
    Optional<PersonType> delete(String id);
}
