package com.alvaro.springcloud.msvc.persons.DTO.response;

import java.util.UUID;

public record PersonName(
        String personName,
        UUID personId
) {
}
