package com.alvaro.springcloud.msvc.persons.controllers;

import com.alvaro.springcloud.msvc.persons.DTO.request.PersonCrudDTO;
import com.alvaro.springcloud.msvc.persons.DTO.response.PersonDataResponseDto;
import com.alvaro.springcloud.msvc.persons.DTO.response.PersonName;
import com.alvaro.springcloud.msvc.persons.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping("/clients")
    public ResponseEntity<?> getPersonName(){
        return ResponseEntity.ok().body(personService.findAllPersonsClient());
    }

    @GetMapping("/person-name/{id}")
    public ResponseEntity<?> getClients(@PathVariable UUID id){
        Optional<PersonName> personOptional = personService.findPersonNameById(id);
        return personOptional.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/searchClients")
    public ResponseEntity<?> searchClients(@RequestParam String term){
        return ResponseEntity.ok().body(personService.findAllPersonsByNameOrDocument(term));
    }


    @GetMapping("/person-clients/{page}")
    public ResponseEntity<?> getPersonsClients(@PathVariable int page){
        Pageable pageable = PageRequest.of(page, 10);
        return ResponseEntity.ok().body(personService.findAllPersonsClients(pageable));
    }

    @GetMapping("/clientes/{page}")
    public ResponseEntity<?> getPersonsClientsPage(@PathVariable int page){
        Pageable pageable = PageRequest.of(page, 10);
        return ResponseEntity.ok().body(personService.findAllPersonsClient(pageable));
    }

    @GetMapping("/suppliers")
    public ResponseEntity<?> getPersonsSuppliers(){
        return ResponseEntity.ok().body(personService.findAllPersonsSupplier());
    }

    @GetMapping("/supplier/{page}")
    public ResponseEntity<?> getPersonsSuppliersPage(@PathVariable int page){
        Pageable pageable = PageRequest.of(page, 10);
        return ResponseEntity.ok().body(personService.findAllPersonsSupplier(pageable));
    }

    @GetMapping("/person-suppliers/{page}")
    public ResponseEntity<?> getPersonsSuppliers(@PathVariable int page){
        Pageable pageable = PageRequest.of(page, 10);
        return ResponseEntity.ok().body(personService.findAllPersonsSuppliers(pageable));
    }

    @GetMapping("/employees")
    public ResponseEntity<?> getPersonsEmployees(){
        return ResponseEntity.ok().body(personService.findAllPersonsEmployees());
    }

    @GetMapping("/employees/{page}")
    public ResponseEntity<?> getPersonsEmployeesPage(@PathVariable int page){
        Pageable pageable = PageRequest.of(page, 10);
        return ResponseEntity.ok().body(personService.findAllPersonsEmployeesPage(pageable));
    }

    @GetMapping("/person-employees/{page}")
    public ResponseEntity<?> getPersonsEmployees(@PathVariable int page){
        Pageable pageable = PageRequest.of(page, 10);
        return ResponseEntity.ok().body(personService.findAllPersonsEmployess(pageable));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<?> getPersonByEmail(@PathVariable String email){
        Optional<PersonDataResponseDto> personOptional = personService.findByEmail(email);
        return personOptional.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPersonByEmail(@PathVariable UUID id){
        Optional<PersonDataResponseDto> personOptional = personService.findPersonById(id);
        return personOptional.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> createPerson(@RequestBody PersonCrudDTO personReq){
        Optional<PersonDataResponseDto> personOptional = personService.savePerson(personReq);
        return personOptional.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(201).build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePerson(@RequestBody PersonCrudDTO personReq, @PathVariable UUID id){
        Optional<PersonDataResponseDto> personOptional = personService.updatePerson(personReq, id);
        return personOptional.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePerson(@PathVariable UUID id){
        Optional<PersonDataResponseDto> personOptional = personService.deletePersonById(id);
        return personOptional.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}