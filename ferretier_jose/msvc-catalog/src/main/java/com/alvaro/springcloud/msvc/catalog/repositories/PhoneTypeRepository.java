package com.alvaro.springcloud.msvc.catalog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alvaro.springcloud.msvc.catalog.entities.PhoneType;

import java.util.Optional;

public interface PhoneTypeRepository extends JpaRepository<PhoneType, String>{
    Optional<PhoneType> findByPhoneTypeId(String phoneTypeId);
    Optional<PhoneType> deleteByPhoneTypeId(String phoneTypeId);
}
