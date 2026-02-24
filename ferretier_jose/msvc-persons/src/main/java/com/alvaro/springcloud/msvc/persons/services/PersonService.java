package com.alvaro.springcloud.msvc.persons.services;

import com.alvaro.springcloud.msvc.persons.DTO.request.PersonCrudDTO;
import com.alvaro.springcloud.msvc.persons.DTO.response.PersonDTO;
import com.alvaro.springcloud.msvc.persons.DTO.response.PersonDataResponseDto;
import com.alvaro.springcloud.msvc.persons.DTO.response.PersonName;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PersonService {
    List<PersonDataResponseDto> findAllPersonsClient();
    Page<PersonDataResponseDto> findAllPersonsClient(Pageable pageable);
    Optional<PersonName> findPersonNameById(UUID id);

    Page<PersonDTO> findAllPersonsClients(Pageable pageable);
    List<PersonDTO> findAllPersonsByNameOrDocument(String term);

    List<PersonDataResponseDto> findAllPersonsSupplier();
    Page<PersonDataResponseDto> findAllPersonsSupplier(Pageable pageable);

    List<PersonDataResponseDto> findAllPersonsEmployees();
    Page<PersonDataResponseDto> findAllPersonsEmployeesPage(Pageable pageable);

    Optional<PersonDataResponseDto> findPersonById(UUID id);
    Optional<PersonDataResponseDto> savePerson(PersonCrudDTO person);
    Optional<PersonDataResponseDto> updatePerson(PersonCrudDTO person, UUID id);
    Optional<PersonDataResponseDto> deletePersonById(UUID id);
    Optional<PersonDataResponseDto> findByEmail(String email);
}