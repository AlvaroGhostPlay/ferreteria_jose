package com.alvaro.springcloud.msvc.persons.services;

import com.alvaro.springcloud.msvc.persons.DTO.request.PersonCudDTO;
import com.alvaro.springcloud.msvc.persons.DTO.response.PersonDTO;
import com.alvaro.springcloud.msvc.persons.DTO.response.PersonDataResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PersonService {
    List<PersonDataResponseDto> findAllPersonsClient();
    Page<PersonDataResponseDto> findAllPersonsClient(Pageable pageable);

    Page<PersonDTO> findAllPersonsClients(Pageable pageable);

    List<PersonDataResponseDto> findAllPersonsSupplier();
    Page<PersonDataResponseDto> findAllPersonsSupplier(Pageable pageable);

    List<PersonDataResponseDto> findAllPersonsEmployees();
    Page<PersonDataResponseDto> findAllPersonsEmployeesPage(Pageable pageable);

    Optional<PersonDataResponseDto> findPersonById(UUID id);
    Optional<PersonDataResponseDto> savePerson(PersonCudDTO person);
    Optional<PersonDataResponseDto> updatePerson(PersonCudDTO person, UUID id);
    Optional<PersonDataResponseDto> deletePersonById(UUID id);
    Optional<PersonDataResponseDto> findByEmail(String email);
}