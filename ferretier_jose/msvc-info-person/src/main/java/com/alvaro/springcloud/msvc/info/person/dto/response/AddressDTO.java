package com.alvaro.springcloud.msvc.info.person.dto.response;

import java.time.LocalDate;
import java.util.UUID;

public record AddressDTO(
        UUID addressId,
        UUID idPerson,
        AddressTypeDTO idAddressType,
        Boolean isPrimary,
        String line1,
        String line2,
        String city,
        String state,
        String country,
        String postalCode,
        String reference,
        Boolean enabled,
        LocalDate createAt,
        LocalDate updateAt
) {
}
