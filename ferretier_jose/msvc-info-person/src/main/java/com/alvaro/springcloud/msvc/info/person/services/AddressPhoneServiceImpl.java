package com.alvaro.springcloud.msvc.info.person.services;

import com.alvaro.springcloud.msvc.info.person.dto.request.AddressPhoneRequest;
import com.alvaro.springcloud.msvc.info.person.dto.response.*;
import com.alvaro.springcloud.msvc.info.person.entities.Address;
import com.alvaro.springcloud.msvc.info.person.entities.Phone;
import com.alvaro.springcloud.msvc.info.person.repositories.AddressRepository;
import com.alvaro.springcloud.msvc.info.person.repositories.PhoneRepository;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDate;
import java.util.*;

@Service
public class AddressPhoneServiceImpl implements AddressPhoneService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private PhoneRepository phoneRepository;

    @Autowired
    private WebClient webClientBuilder;

    @Transactional(readOnly = true)
    @Override
    public Optional<AddressPhoneResponse> findByIdAll(UUID id) {
        AddressPhoneResponse response = new AddressPhoneResponse();

        List<AddressDTO> addresses = addressRepository.findByPersonId(id).stream().map(this::mapperAddresDTO).toList();
        List<PhoneDTO> phones = phoneRepository.findByPersonId(id).stream().map(this::mapperPhoneDTO).toList();

        if (addresses == null && phones ==null) {
            return Optional.empty();
        }

        response.setAddresses(addresses);
        response.setPhones(phones);

        return Optional.of(response);
    }

    @Transactional(readOnly = true)
    @Override
    public List<AddressDTO> findByIdAllAddress(UUID id) {
        return addressRepository.findByPersonId(id).stream().map(this::mapperAddresDTO).toList();
    }

    @Transactional(readOnly = true)
    @Override
    public List<PhoneDTO> findByIdAllPhones(UUID id) {
        return phoneRepository.findByPersonId(id).stream().map(this::mapperPhoneDTO).toList();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<AddressDTO> getAddress(UUID id) {
        Optional<AddressDTO> addressDTO = addressRepository.findByAddressId(id).map(this::mapperAddresDTO);
        return addressDTO;
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<PhoneDTO> getPhone(UUID id) {
        Optional<PhoneDTO> phoneOP = phoneRepository.findByPhoneId(id).map(this::mapperPhoneDTO);
        return phoneOP;
    }

    @Transactional
    @Override
    public Optional<AddressPhoneResponse> save(AddressPhoneRequest request) {
        List<AddressDTO> addresses = new ArrayList<>();
        if (request.getAddresses() != null) {
            for (Address ar : request.getAddresses()) {
                Address a = new Address();
                a.setIdPerson(ar.getIdPerson());
                a.setIdAddressType(ar.getIdAddressType());
                a.setCity(ar.getCity());
                a.setCountry(ar.getCountry());
                a.setPostalCode(ar.getPostalCode());
                a.setState(ar.getState());
                a.setLine1(ar.getLine1());
                a.setLine2(ar.getLine2());
                a.setCreateAt(LocalDate.now());
                a.setUpdateAt(LocalDate.now());
                a.setPrimary(ar.getPrimary());
                a.setReference(ar.getReference());
                a.setEnabled(ar.getEnabled());
                addressRepository.save(a);
                addresses.add(mapperAddresDTO(a));
            }
        }

            List<PhoneDTO> phones = new ArrayList<>();
        if (request.getPhones() != null) {
            for (Phone pn : request.getPhones()) {
                Phone p = new Phone();
                p.setIdPerson(pn.getIdPerson());
                p.setIdPhoneType(pn.getIdPhoneType());
                p.setPhoneNumber(pn.getPhoneNumber());
                p.setEnabled(pn.getEnabled());
                p.setCreateAt(LocalDate.now());
                p.setUpdateAt(LocalDate.now());
                phoneRepository.save(p);
                phones.add(mapperPhoneDTO(p));
            }

        }
        AddressPhoneResponse response = new AddressPhoneResponse(addresses, phones);
        return Optional.of(response);
    }

    @Transactional
    @Override
    public Optional<AddressPhoneResponse> update(AddressPhoneRequest request) {
        List<AddressDTO> addresses = new ArrayList<>();
        if (request.getAddresses() != null) {
            for (Address ar : request.getAddresses()) {
                Address a = addressRepository.findByAddressId(ar.getAddressId()).orElseThrow();
                a.setIdPerson(ar.getIdPerson());
                a.setIdAddressType(ar.getIdAddressType());
                a.setCity(ar.getCity());
                a.setCountry(ar.getCountry());
                a.setPostalCode(ar.getPostalCode());
                a.setState(ar.getState());
                a.setLine1(ar.getLine1());
                a.setLine2(ar.getLine2());
                a.setUpdateAt(LocalDate.now());
                a.setPrimary(ar.getPrimary());
                a.setReference(ar.getReference());
                a.setEnabled(ar.getEnabled());
                addressRepository.save(a);
                addresses.add(mapperAddresDTO(a));
            }
        }

            List<PhoneDTO> phones = new ArrayList<>();
        if (request.getPhones() != null) {
            for (Phone pn : request.getPhones()) {
                Phone p = phoneRepository.findByPhoneId(pn.getPhoneId()).orElseThrow();
                p.setIdPerson(pn.getIdPerson());
                p.setIdPhoneType(pn.getIdPhoneType());
                p.setPhoneNumber(pn.getPhoneNumber());
                p.setEnabled(pn.getEnabled());
                p.setUpdateAt(LocalDate.now());
                phoneRepository.save(p);
                phones.add(mapperPhoneDTO(p));
            }

        }
        AddressPhoneResponse response = new AddressPhoneResponse(addresses, phones);
        return Optional.of(response);
    }

    @Transactional
    @Override
    public Optional<AddressPhoneResponse> deleteAll(UUID id) {
        AddressPhoneResponse response = new AddressPhoneResponse();

        List<AddressDTO> addresses = addressRepository.findByPersonId(id).stream().map(this::mapperAddresDTO).toList();
        List<PhoneDTO> phones = phoneRepository.findByPersonId(id).stream().map(this::mapperPhoneDTO).toList();
        
        if (addresses.isEmpty() && phones.isEmpty()) {
            return Optional.empty();
        }
        
        addressRepository.deleteByPersonId(id);
        phoneRepository.deleteByIdPerson(id);

        response.setAddresses(addresses);
        response.setPhones(phones);
        return Optional.of(response);
    }

    @Transactional
    @Override
    public List<AddressDTO> deleteAllAddress(UUID id) {
        Optional<Address> address = addressRepository.findFirstByIdPerson(id);
        if (address.isPresent()) {
            List<AddressDTO> addresses = addressRepository.findByPersonId(id).stream().map(this::mapperAddresDTO)
                    .toList();
            addressRepository.deleteByPersonId(id);
            return addresses;
        }
        return null;
    }

    @Transactional
    @Override
    public List<PhoneDTO> deleteAllPhones(UUID id) {
        Optional<Phone> phone = phoneRepository.findFirstByIdPerson(id);
        if (phone.isPresent()) {
            List<PhoneDTO> phones = phoneRepository.findByPersonId(id).stream().map(this::mapperPhoneDTO).toList();
            addressRepository.deleteByPersonId(id);
            return phones;
        }
        return null;
    }

    @Transactional
    @Override
    public Optional<AddressDTO> saveAddress(Address request) {
        String uri = "/addres-type";
        Optional<AddressTypeDTO> type = getCatalog(uri, request.getIdAddressType(), AddressTypeDTO.class);
        if (type.isEmpty()) {

        }
        Optional<Address> error = addressRepository.findByIdPersonAndIdAddressType(request.getIdPerson(),
                request.getIdAddressType());
        if (error.isPresent()) {

        }

        Address a = new Address();
        a.setIdPerson(request.getIdPerson());
        a.setIdAddressType(request.getIdAddressType());
        a.setCity(request.getCity());
        a.setCountry(request.getCountry());
        a.setPostalCode(request.getPostalCode());
        a.setState(request.getState());
        a.setLine1(request.getLine1());
        a.setLine2(request.getLine2());
        a.setCreateAt(LocalDate.now());
        a.setUpdateAt(LocalDate.now());
        a.setPrimary(request.getPrimary());
        a.setReference(request.getReference());
        a.setEnabled(request.getEnabled());
        return Optional.of(mapperAddresDTO(addressRepository.save(a)));
    }

    @Transactional
    @Override
    public Optional<AddressDTO> updateAddress(Address request, UUID id) {
        Optional<Address> addressDb = addressRepository.findByAddressId(id);
        if (addressDb.isPresent()) {
            addressDb.get().setIdPerson(request.getIdPerson());
            addressDb.get().setIdAddressType(request.getIdAddressType());
            addressDb.get().setCity(request.getCity());
            addressDb.get().setCountry(request.getCountry());
            addressDb.get().setPostalCode(request.getPostalCode());
            addressDb.get().setState(request.getState());
            addressDb.get().setLine1(request.getLine1());
            addressDb.get().setLine2(request.getLine2());
            addressDb.get().setUpdateAt(LocalDate.now());
            addressDb.get().setPrimary(request.getPrimary());
            addressDb.get().setReference(request.getReference());
            addressDb.get().setEnabled(request.getEnabled());
            return Optional.of(mapperAddresDTO(addressDb.get()));
        }
        return Optional.empty();
    }

    @Transactional
    @Override
    public Optional<AddressDTO> deleteAddress(UUID id) {
        Optional<Address> addressDb = addressRepository.findByAddressId(id);
        if (addressDb.isPresent()) {
            addressRepository.deleteByPersonId(id);
            return Optional.of(mapperAddresDTO(addressDb.get()));
        }
        return Optional.empty();
    }

    @Transactional
    @Override
    public Optional<PhoneDTO> savePhone(Phone request) {
        String uri = "/phone-type";
        Optional<PhoneTypeDTO> type = getCatalog(uri, request.getIdPhoneType(), PhoneTypeDTO.class);
        if (type.isEmpty()) {

        }
        Optional<Phone> error = phoneRepository.findByIdPersonAndPhoneNumber(request.getIdPerson(),
                request.getIdPhoneType());
        if (error.isPresent()) {
            // lanzar 2 excepcxoiens dependiendo del caso
        }

        Phone p = new Phone();
        p.setIdPerson(request.getIdPerson());
        p.setIdPhoneType(request.getIdPhoneType());
        p.setPhoneNumber(request.getPhoneNumber());
        p.setEnabled(request.getEnabled());
        p.setCreateAt(LocalDate.now());
        p.setUpdateAt(LocalDate.now());

        return Optional.of(mapperPhoneDTO(phoneRepository.save(p)));
    }

    @Transactional
    @Override
    public Optional<PhoneDTO> updatePhone(Phone request, UUID id) {
        Optional<Phone> phoneOP = phoneRepository.findByPhoneId(id);
        if (phoneOP.isPresent()) {
            Optional<Phone> error = phoneRepository.findByPhone(request.getPhoneNumber());
            if (error.isPresent()) {
                // lanzar error
            }
            phoneOP.get().setUpdateAt(LocalDate.now());
            phoneOP.get().setEnabled(request.getEnabled());
            phoneOP.get().setPhoneNumber(request.getPhoneNumber());
            phoneOP.get().setIdPerson(request.getIdPerson());
            return Optional.of(mapperPhoneDTO(phoneOP.get()));
        }
        return Optional.empty();
    }

    @Transactional
    @Override
    public Optional<PhoneDTO> deletePhone(UUID id) {
        Optional<Phone> phoneOP = phoneRepository.findByPhoneId(id);
        if (phoneOP.isPresent()) {
            phoneRepository.deleteByPhoneId(id);
            return Optional.of(mapperPhoneDTO(phoneOP.get()));
        }
        return Optional.empty();
    }

    private AddressDTO mapperAddresDTO(Address address) {
        String uri = "/addres-type";
        Optional<AddressTypeDTO> addressTypeDTO = getCatalog(uri, address.getIdAddressType(), AddressTypeDTO.class);

        return new AddressDTO(address.getAddressId(),
                address.getIdPerson(),
                addressTypeDTO.orElse(null),
                address.getPrimary(),
                address.getLine1(),
                address.getLine2(),
                address.getCity(),
                address.getState(),
                address.getCountry(),
                address.getPostalCode(),
                address.getReference(),
                address.getEnabled(),
                address.getCreateAt(),
                address.getUpdateAt());
    }

    private PhoneDTO mapperPhoneDTO(Phone phone) {
        String uri = "/phone-type";
        Optional<PhoneTypeDTO> phoneDTOTypeDTO = getCatalog(uri, phone.getIdPhoneType(), PhoneTypeDTO.class);

        return new PhoneDTO(
                phone.getPhoneId(),
                phone.getIdPerson(),
                phoneDTOTypeDTO.orElse(null),
                phone.getPhoneNumber(),
                phone.getEnabled(),
                phone.getCreateAt(),
                phone.getUpdateAt());
    }

    @NotNull
    private <T> Optional<T> getCatalog(String uri, Object id, Class<T> responseType) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);

        return webClientBuilder.get()
                .uri("http://msvc-catalog" + uri + "/{id}", params)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(responseType) // Se usa el parámetro Class<T>
                .blockOptional();
    }
}
