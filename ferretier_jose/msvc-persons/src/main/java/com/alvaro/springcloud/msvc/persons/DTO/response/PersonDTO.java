package com.alvaro.springcloud.msvc.persons.DTO.response;

import java.time.LocalDate;
import java.util.UUID;

public record PersonDTO(
        UUID personId,
        String idPersonType,
        Boolean isClient,
        Boolean isSupplier,
        Boolean isEmployee,
        Boolean enabled,
        LocalDate crateAt,
        String email
) {
}
