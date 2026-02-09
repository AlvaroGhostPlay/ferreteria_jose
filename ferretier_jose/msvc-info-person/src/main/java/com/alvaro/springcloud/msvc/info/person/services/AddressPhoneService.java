package com.alvaro.springcloud.msvc.info.person.services;

import com.alvaro.springcloud.msvc.info.person.dto.request.AddressPhoneRequest;
import com.alvaro.springcloud.msvc.info.person.dto.response.AddressDTO;
import com.alvaro.springcloud.msvc.info.person.dto.response.AddressPhoneResponse;
import com.alvaro.springcloud.msvc.info.person.dto.response.PhoneDTO;
import com.alvaro.springcloud.msvc.info.person.entities.Address;
import com.alvaro.springcloud.msvc.info.person.entities.Phone;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AddressPhoneService {
    Optional<AddressPhoneResponse> findByIdAll(UUID id);
    List<AddressDTO> findByIdAllAddress(UUID id);
    List<PhoneDTO> findByIdAllPhones(UUID id);

    Optional<AddressPhoneResponse> save(AddressPhoneRequest request);
    Optional<AddressPhoneResponse> update(AddressPhoneRequest request);
    Optional<AddressPhoneResponse> deleteAll(UUID id);
    List<AddressDTO> deleteAllAddress(UUID id);
    List<PhoneDTO> deleteAllPhones(UUID id);

    Optional<AddressDTO> getAddress(UUID id);
    Optional<AddressDTO> saveAddress(Address request);
    Optional<AddressDTO> updateAddress(Address request, UUID id);
    Optional<AddressDTO> deleteAddress(UUID id);

    Optional<PhoneDTO> getPhone(UUID id);
    Optional<PhoneDTO> savePhone(Phone request);
    Optional<PhoneDTO> updatePhone(Phone request, UUID id);
    Optional<PhoneDTO> deletePhone(UUID id);
}
