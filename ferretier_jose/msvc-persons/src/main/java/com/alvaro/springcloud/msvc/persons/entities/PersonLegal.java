package com.alvaro.springcloud.msvc.persons.entities;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "persons_legals")
public class PersonLegal {
    @Id
    @Column(name = "id_person")
    private UUID personId;
    private String legalName;
    private String comercialName;

    @Column(name = "id_document_type_representative", nullable = false, length = 1)
    private String idTypeDocument;
    @Column(name = "document_representative", nullable = false, length = 18)
    private String representativeLegalDocument;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "id_person")
    private Person person;

    public UUID getPersonId() {
        return personId;
    }

    public void setPersonNaturalId(UUID personId) {
        this.personId = personId;
    }

    public String getLegalName() {
        return legalName;
    }

    public void setLegalName(String legalName) {
        this.legalName = legalName;
    }

    public String getComercialName() {
        return comercialName;
    }

    public void setComercialName(String comercialName) {
        this.comercialName = comercialName;
    }

    public String getIdTypeDocument() {
        return idTypeDocument;
    }

    public void setIdTypeDocument(String idTypeDocument) {
        this.idTypeDocument = idTypeDocument;
    }

    public String getRepresentativeLegalDocument() {
        return representativeLegalDocument;
    }

    public void setRepresentativeLegalDocument(String representativeLegalDocument) {
        this.representativeLegalDocument = representativeLegalDocument;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public void setPersonId(UUID personId) {
        this.personId = personId;
    }
}