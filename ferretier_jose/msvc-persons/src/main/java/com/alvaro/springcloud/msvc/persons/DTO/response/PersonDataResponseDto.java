package com.alvaro.springcloud.msvc.persons.DTO.response;

public class PersonDataResponseDto {
    private PersonDTO person;
    private PersonNaturalDTO personNatural;
    private PersonLegalDTO personLegal;

    public PersonDTO getPerson() {
        return person;
    }

    public void setPerson(PersonDTO person) {
        this.person = person;
    }

    public PersonNaturalDTO getPersonNatural() {
        return personNatural;
    }

    public void setPersonNatural(PersonNaturalDTO personNatural) {
        this.personNatural = personNatural;
    }

    public PersonLegalDTO getPersonLegal() {
        return personLegal;
    }

    public void setPersonLegal(PersonLegalDTO personLegal) {
        this.personLegal = personLegal;
    }
}
