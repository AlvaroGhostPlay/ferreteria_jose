package com.alvaro.springcloud.msvc.persons.DTO.response;

import java.util.List;
import java.util.UUID;

public record PersonDTO(
        UUID personId,
        Boolean enabled,
        String name,
        String email,
        PersonTypeDTO person,
        DocumentTypeDTO documentType,
        String documentPerson,
        List<String> phones
) {
}
