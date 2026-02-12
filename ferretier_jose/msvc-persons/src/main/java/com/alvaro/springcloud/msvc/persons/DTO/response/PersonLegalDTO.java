package com.alvaro.springcloud.msvc.persons.DTO.response;

import java.util.UUID;

public record PersonLegalDTO(
        UUID personId,
        String legalName,
        String comercialName,
        DocumentTypeDTO documentTypeRepre,
        String representativeLegalDocument
) {
}
