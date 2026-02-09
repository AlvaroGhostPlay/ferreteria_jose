package com.alvaro.springcloud.msvc.persons.services;

import com.alvaro.springcloud.msvc.persons.DTO.request.PersonCudDTO;
import com.alvaro.springcloud.msvc.persons.DTO.request.PersonKind;
import com.alvaro.springcloud.msvc.persons.DTO.response.*;
import com.alvaro.springcloud.msvc.persons.entities.Person;
import com.alvaro.springcloud.msvc.persons.entities.PersonLegal;
import com.alvaro.springcloud.msvc.persons.entities.PersonNatural;
import com.alvaro.springcloud.msvc.persons.repositories.PersonLegalRespository;
import com.alvaro.springcloud.msvc.persons.repositories.PersonNaturalRepository;
import com.alvaro.springcloud.msvc.persons.repositories.PersonRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDate;
import java.util.*;

import java.util.function.Function;
import java.util.function.Supplier;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private PersonLegalRespository personLegalRespository;
    @Autowired
    private PersonNaturalRepository personNaturalRepository;

    @Autowired
    private WebClient webClientBuilder;

    @Transactional(readOnly = true)
    @Override
    public List<PersonDataResponseDto> findAllPersonsClient() {
        return mapList(personRepository::findByIsClient);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<PersonDataResponseDto> findAllPersonsClient(Pageable pageable) {
        return mapPage(personRepository::findByIsClientPage, pageable);
    }

    @Transactional(readOnly = true)
    @Override
    public List<PersonDataResponseDto> findAllPersonsSupplier() {
        return mapList(personRepository::findByIsSupplier);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<PersonDataResponseDto> findAllPersonsSupplier(Pageable pageable) {
        return mapPage(personRepository::findByIsSupplierPage, pageable);
    }

    @Transactional(readOnly = true)
    @Override
    public List<PersonDataResponseDto> findAllPersonsEmployees() {
        return mapList(personRepository::findByIsEmployee);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<PersonDataResponseDto> findAllPersonsEmployeesPage(Pageable pageable) {
        return mapPage(personRepository::findByIsEmployeePage, pageable);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<PersonDataResponseDto> findPersonById(UUID id) {
        return mapOptional(personRepository.findByIdEsp(id).get());
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<PersonDataResponseDto> findByEmail(String email) {
        return mapOptional(personRepository.findByEmail(email).get());
    }

    @Transactional
    @Override
    public Optional<PersonDataResponseDto> savePerson(PersonCudDTO personReq) {

        if(personReq.getKind() == PersonKind.NATURAL){
            if (personReq.getPersonNaturalData() == null){
                throw new IllegalArgumentException("natural es requerido cuando kind=NATURAL");
            }
            if (personReq.getPersonLegalData() != null){
                throw new IllegalArgumentException("legal debe ser null cuando kind=NATURAL");
            }
        } else{
            if (personReq.getPersonLegalData() == null) {
                throw new IllegalArgumentException("legal es requerido cuando kind=LEGAL");
            }
            if (personReq.getPersonNaturalData() != null) {
                throw new IllegalArgumentException("natural debe ser null cuando kind=LEGAL");
            }
        }

        Person p = new Person();
        return savePerson(personReq, p, null);

    }

    @Transactional
    @Override
    public Optional<PersonDataResponseDto> updatePerson(PersonCudDTO personReq, UUID id) {
        if(personReq.getKind() == PersonKind.NATURAL){
            if (personReq.getPersonNaturalData() == null){
                throw new IllegalArgumentException("natural es requerido cuando kind=NATURAL");
            }
            if (personReq.getPersonLegalData() != null){
                throw new IllegalArgumentException("legal debe ser null cuando kind=NATURAL");
            }
        } else{
            if (personReq.getPersonLegalData() == null) {
                throw new IllegalArgumentException("legal es requerido cuando kind=LEGAL");
            }
            if (personReq.getPersonNaturalData() != null) {
                throw new IllegalArgumentException("natural debe ser null cuando kind=LEGAL");
            }
        }

        Optional<Person> presonDb = personRepository.findById(id);

        if (presonDb.isPresent()) {
            return savePerson(personReq, presonDb.get(), id);
        } else {
            return Optional.empty();
        }
    }

    @Transactional
    @Override
    public Optional<PersonDataResponseDto> deletePersonById(UUID id) {
        Optional<Person> presonDb = personRepository.findById(id);
        if (presonDb.isPresent()) {
            Optional<PersonDataResponseDto> response = mapOptional(presonDb.get());
            personNaturalRepository.findById(id).ifPresent(personNaturalRepository::delete);
            personLegalRespository.findById(id).ifPresent(personLegalRespository::delete);
            personRepository.deleteById(id);
            return response;
        } else {
            return Optional.empty();
        }
    }




    @NotNull
    private static PersonDTO getPersonDTO(Person person) {
        PersonDTO personDTO = new PersonDTO(
                person.getPersonId(),
                person.getIdPersonType(),
                person.getClient(),
                person.getSupplier(),
                person.getEmployee(),
                person.getEnabled(),
                person.getCrateAt(),
                person.getEmail());
        return personDTO;
    }

    @NotNull
    private PersonLegalDTO getPersonLegalDTO(PersonLegal personLegal) {
        PersonLegalDTO personLegalDTO;
        String uri = "/person-type";
        Optional<?> document = getCatalog(uri, personLegal.getTypeDocument(), DocumentTypeDTO.class);
        DocumentTypeDTO documentType = null;
        if (document.isPresent()) {
            documentType = (DocumentTypeDTO) document.get();
        }

        uri = "/person-type";
        document = getCatalog(uri, personLegal.getTypeDocument(), DocumentTypeDTO.class);
        DocumentTypeDTO documentTypeRepre = null;
        if (document.isPresent()) {
            documentTypeRepre = (DocumentTypeDTO) document.get();
        }

        personLegalDTO = new PersonLegalDTO(
                personLegal.getPersonId(),
                personLegal.getLegalName(),
                personLegal.getComercialName(),
                documentType,
                personLegal.getNit(),
                documentTypeRepre,
                personLegal.getRepresentativeLegalDocument());
        return personLegalDTO;
    }

    @NotNull
    private PersonNaturalDTO getPersonNaturalDTO(PersonNatural personNatural) {
        PersonNaturalDTO personNaturalDTO;
        String uri = "/person-type";
        Optional<?> document = getCatalog(uri, personNatural.getIdDocumentType(), DocumentTypeDTO.class);
        DocumentTypeDTO documentType = null;
        if (document.isPresent()) {
            documentType = (DocumentTypeDTO) document.get();
        }

        uri = "/gener";
        document = getCatalog(uri, personNatural.getIdGener(), GenerDTO.class);
        GenerDTO gener = null;
        if (document.isPresent()) {
            gener = (GenerDTO) document.get();
        }

        uri = "/status";
        document = getCatalog(uri, personNatural.getIdSocialStatus(), StatusSocialDTO.class);
        StatusSocialDTO status = null;
        if (document.isPresent()) {
            status = (StatusSocialDTO) document.get();
        }

        personNaturalDTO = new PersonNaturalDTO(
                personNatural.getPersonNaturalId(),
                personNatural.getFirstName(),
                personNatural.getMiddleName(),
                personNatural.getThirdName(),
                personNatural.getLastName(),
                personNatural.getSeccondLastName(),
                personNatural.getMarriedLastName(),
                gener,
                status,
                documentType,
                personNatural.getDocumentPerson());
        return personNaturalDTO;
    }

    @NotNull
    private <T> Optional<T> getCatalog(String uri, String id, Class<T> responseType) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);

        return webClientBuilder.get()
                .uri("http://msvc-catalog" + uri + "/{id}", params)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(responseType) // Se usa el parámetro Class<T>
                .blockOptional();
    }

    private PersonDataResponseDto toPersonDataDto(Person person) {
        PersonLegalDTO personLegalDTO = null;
        PersonNaturalDTO personNaturalDTO = null;

        PersonDTO personDTO = getPersonDTO(person);

        PersonLegal personLegal = person.getPersonLegal();
        if (personLegal != null) {
            personLegalDTO = getPersonLegalDTO(personLegal);
        }

        PersonNatural personNatural = person.getPersonNatural();
        if (personNatural != null) {
            personNaturalDTO = getPersonNaturalDTO(personNatural);
        }

        PersonDataResponseDto dto = new PersonDataResponseDto();
        dto.setPerson(personDTO);
        dto.setPersonNatural(personNaturalDTO);
        dto.setPersonLegal(personLegalDTO);
        return dto;
    }

    private List<PersonDataResponseDto> mapList(Supplier<List<Person>> fetcher) {
        return fetcher.get()
                .stream()
                .map(this::toPersonDataDto)
                .toList();
    }

    private Page<PersonDataResponseDto> mapPage(Function<Pageable, Page<Person>> fetcher, Pageable pageable) {
        return fetcher.apply(pageable)
                .map(this::toPersonDataDto);
    }

    private Optional<PersonDataResponseDto> mapOptional(Person fetcher) {
        PersonDataResponseDto personDataResponseDto = toPersonDataDto(fetcher);
        return Optional.of(personDataResponseDto);
    }

    @NotNull
    private Optional<PersonDataResponseDto> savePerson(PersonCudDTO personReq, Person presonDb, UUID id) {
        Person p = presonDb;

        p.setIdPersonType(personReq.getIdPersonType());
        p.setClient(Boolean.TRUE.equals(personReq.getClient()));
        p.setSupplier(Boolean.TRUE.equals(personReq.getSupplier()));
        p.setEmployee(Boolean.TRUE.equals(personReq.getEmployee()));
        p.setEnabled(personReq.getEnabled());
        p.setEmail(personReq.getEmail());
        p.setCrateAt(LocalDate.now());
        personRepository.save(p);

        if (personReq.getKind() == PersonKind.NATURAL) {
            PersonNatural pn = null;
            if (id == null){
                pn = new PersonNatural();
            } else {
                pn = personNaturalRepository.findById(id).orElse(null);
                if (pn == null) {
                    return Optional.empty();
                }
            }
            pn.setPerson(p); // <- con @MapsId esto fija id_person = p.id

            var nd = personReq.getPersonNaturalData();
            pn.setFirstName(nd.getFirstName());
            pn.setMiddleName(nd.getMiddleName());
            pn.setThirdName(nd.getThirdName());
            pn.setLastName(nd.getLastName());
            pn.setSeccondLastName(nd.getSecondLastName());
            pn.setMarriedLastName(nd.getMarriedLastName());
            pn.setIdGener(nd.getIdGener());
            pn.setIdSocialStatus(nd.getIdSocialStatus());
            pn.setIdDocumentType(nd.getIdDocumentType());
            pn.setDocumentPerson(nd.getDocumentPerson());
            personLegalRespository.deleteById(p.getPersonId());
            personNaturalRepository.save(pn);
        } else {
            PersonLegal l = null;
            if (id == null){
                l = new PersonLegal();
            } else {
                l = personLegalRespository.findById(id).orElse(null);
                if (l == null) {
                    return Optional.empty();
                }
            }
            l.setPerson(p);

            var ld = personReq.getPersonLegalData();
            l.setLegalName(ld.getLegalName());
            l.setComercialName(ld.getComercialName());
            l.setIdTypeDocument(ld.getIdTypeDocument());
            l.setNit(ld.getNit());
            l.setRepresentativeLegalDocument(ld.getRepresentativeLegalDocument());
            personNaturalRepository.deleteById(p.getPersonId());
            personLegalRespository.save(l);
        }
        return mapOptional(p);
    }
}
