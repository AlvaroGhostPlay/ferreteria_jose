package com.alvaro.springcloud.msvc.persons.DTO.response;

import java.util.UUID;

public record PersonNaturalDTO(
        UUID personNaturalId,
        String firstName,
        String middleName,
        String thirdName,
        String lastName,
        String seccondLastName,
        String marriedLastName,
        GenerDTO Gener,
        StatusSocialDTO statusSocial,
        DocumentTypeDTO DocumentType,
        String documentPerson
) {
}
