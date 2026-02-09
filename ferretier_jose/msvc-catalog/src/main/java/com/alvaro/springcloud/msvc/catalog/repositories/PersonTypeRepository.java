package com.alvaro.springcloud.msvc.catalog.repositories;

import com.alvaro.springcloud.msvc.catalog.entities.PersonType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonTypeRepository extends JpaRepository<PersonType, String>{
    Optional<PersonType> findByPersonTypeId(String id);
    Optional<PersonType> deleteByPersonTypeId(String id);
}
