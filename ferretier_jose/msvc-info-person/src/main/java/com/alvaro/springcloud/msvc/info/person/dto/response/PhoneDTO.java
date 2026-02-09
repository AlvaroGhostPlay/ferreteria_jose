package com.alvaro.springcloud.msvc.info.person.dto.response;

import java.time.LocalDate;
import java.util.UUID;

public record PhoneDTO(
        UUID phoneId,
        UUID idPerson,
        PhoneTypeDTO type,
        String phoneNumber,
        Boolean enabled,
        LocalDate createAt,
        LocalDate updateAt
) {
}
