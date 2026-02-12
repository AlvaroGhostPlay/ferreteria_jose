package com.alvaro.springcloud.msvc.persons.entities;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "persons_naturals")
public class PersonNatural {
    @Id
    @Column(name = "id_person")
    private UUID personNaturalId;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "middle_name")
    private String middleName;
    @Column(name = "third_name")
    private String thirdName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "second_last_name")
    private String seccondLastName;
    @Column(name = "married_last_name")
    private String marriedLastName;
    @Column(name = "id_gener")
    private String idGener;
    @Column(name = "id_social_status")
    private String idSocialStatus;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name="id_person")
    private Person person;

    public UUID getPersonNaturalId() {
        return personNaturalId;
    }

    public void setPersonNaturalId(UUID personNaturalId) {
        this.personNaturalId = personNaturalId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getThirdName() {
        return thirdName;
    }

    public void setThirdName(String thirdName) {
        this.thirdName = thirdName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSeccondLastName() {
        return seccondLastName;
    }

    public void setSeccondLastName(String seccondLastName) {
        this.seccondLastName = seccondLastName;
    }

    public String getMarriedLastName() {
        return marriedLastName;
    }

    public void setMarriedLastName(String marriedLastName) {
        this.marriedLastName = marriedLastName;
    }

    public String getIdGener() {
        return idGener;
    }

    public void setIdGener(String idGener) {
        this.idGener = idGener;
    }

    public String getIdSocialStatus() {
        return idSocialStatus;
    }

    public void setIdSocialStatus(String idSocialStatus) {
        this.idSocialStatus = idSocialStatus;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}