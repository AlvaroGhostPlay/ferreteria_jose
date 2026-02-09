package com.alvaro.springcloud.msvc.persons.repositories;

import com.alvaro.springcloud.msvc.persons.entities.PersonNatural;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PersonNaturalRepository extends JpaRepository<PersonNatural, UUID> {
}
