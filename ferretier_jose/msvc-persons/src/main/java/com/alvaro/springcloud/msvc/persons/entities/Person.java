package com.alvaro.springcloud.msvc.persons.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(
        name = "persons",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_persons_id_document_type_person_document",
                        columnNames = {"id_document_type", "person_document"}
                )
        }
)
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "person_id", updatable = false, nullable = false)
    private UUID personId;
    private String idPersonType;
    private Boolean isClient;
    private Boolean isSupplier;
    private Boolean isEmployee;
    private Boolean enabled;
    private LocalDate crateAt;
    private String name;

    @Email
    @Column(unique = true, nullable = false)
    private String email;

    @Column(name = "id_document_type", nullable = false, length = 1)
    @NotBlank
    private String idDocumentType;

    @Column(name = "person_document", nullable = false, length = 18)
    @NotBlank
    private String personDocument;

    @OneToOne(mappedBy = "person",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY)
    private PersonNatural personNatural;

    @OneToOne(mappedBy = "person",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY)
    private PersonLegal personLegal;

    public UUID getPersonId() {
        return personId;
    }

    public void setPersonId(UUID personId) {
        this.personId = personId;
    }

    public String getIdPersonType() {
        return idPersonType;
    }

    public void setIdPersonType(String idPersonType) {
        this.idPersonType = idPersonType;
    }

    public Boolean getClient() {
        return isClient;
    }

    public void setClient(Boolean client) {
        isClient = client;
    }

    public Boolean getSupplier() {
        return isSupplier;
    }

    public void setSupplier(Boolean supplier) {
        isSupplier = supplier;
    }

    public Boolean getEmployee() {
        return isEmployee;
    }

    public void setEmployee(Boolean employee) {
        isEmployee = employee;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public LocalDate getCrateAt() {
        return crateAt;
    }

    public void setCrateAt(LocalDate crateAt) {
        this.crateAt = crateAt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public PersonNatural getPersonNatural() {
        return personNatural;
    }

    public void setPersonNatural(PersonNatural personNatural) {
        this.personNatural = personNatural;
    }

    public PersonLegal getPersonLegal() {
        return personLegal;
    }

    public void setPersonLegal(PersonLegal personLegal) {
        this.personLegal = personLegal;
    }

    public String getIdDocumentType() {
        return idDocumentType;
    }

    public void setIdDocumentType(String idDocumentType) {
        this.idDocumentType = idDocumentType;
    }

    public String getPersonDocument() {
        return personDocument;
    }

    public void setPersonDocument(String personDocument) {
        this.personDocument = personDocument;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}