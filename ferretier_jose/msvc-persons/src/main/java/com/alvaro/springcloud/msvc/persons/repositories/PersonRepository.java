package com.alvaro.springcloud.msvc.persons.repositories;

import com.alvaro.springcloud.msvc.persons.entities.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PersonRepository extends JpaRepository<Person, UUID> {
    @Query("""
        select p from Person p where p.isClient = true 
        """)
    List<Person> findByIsClient();

    @Query("select p from Person p where p.isClient = true")
    Page<Person> findByIsClientPage(Pageable pageable);

    @Query("""
        select p from Person p where p.isSupplier = true 
        """)
    List<Person> findByIsSupplier();

    @Query("select p from Person p where p.isSupplier = true")
    Page<Person> findByIsSupplierPage(Pageable pageable);

    @Query("""
        select p from Person p where p.isEmployee= true 
        """)
    List<Person> findByIsEmployee();

    @Query("select p from Person p where p.isEmployee = true")
    Page<Person> findByIsEmployeePage(Pageable pageable);

    @Query("""
        select p from Person p
            where p.personId = :id
        """)
    Optional<Person> findByIdEsp(UUID id);

    @Query("""
        select p from Person p
            where p.email = :email
        """)
    Optional<Person> findByEmail(@Param("email") String email);

    List<Person> findByNameContainingIgnoreCaseOrPersonDocumentContainingIgnoreCase(String name, String document);
}