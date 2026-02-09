package com.alvaro.springcloud.msvc.persons.repositories;

import com.alvaro.springcloud.msvc.persons.entities.PersonLegal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PersonLegalRespository extends JpaRepository<PersonLegal, UUID> {
}
