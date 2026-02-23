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
    private WebClient.Builder webClientBuilder;

    @Override
    @Transactional(readOnly = true)
    public List<String> findPhonesByIdPerson(UUID id) {
        List<Phone> phones = phoneRepository.findByIdPerson(id);
        List<String> phonesOnly = new ArrayList<>();
        for (Phone p : phones) {
            phonesOnly.add(p.getPhoneNumber());
        }
        return phonesOnly;
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<AddressPhoneResponse> findByIdAll(UUID id) {
        AddressPhoneResponse response = new AddressPhoneResponse();

        List<AddressDTO> addresses = addressRepository.findByIdPerson(id).stream().map(this::mapperAddresDTO).toList();
        List<PhoneDTO> phones = phoneRepository.findByIdPerson(id).stream().map(this::mapperPhoneDTO).toList();

        System.out.println(addresses);
        System.out.println(phones);

        if (addresses == null && phones == null) {
            return Optional.empty();
        }

        response.setAddresses(addresses);
        response.setPhones(phones);

        return Optional.of(response);
    }

    @Transactional(readOnly = true)
    @Override
    public List<AddressDTO> findByIdAllAddress(UUID id) {
        return addressRepository.findByIdPerson(id).stream().map(this::mapperAddresDTO).toList();
    }

    @Transactional(readOnly = true)
    @Override
    public List<PhoneDTO> findByIdAllPhones(UUID id) {
        return phoneRepository.findByIdPerson(id).stream().map(this::mapperPhoneDTO).toList();
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

    /*
     * @Transactional
     * 
     * @Override
     * public Optional<AddressPhoneResponse> update(UUID idPerson,
     * AddressPhoneRequest request) {
     * 
     * // ===== ADDRESSES =====
     * List<Address> dbAddresses = addressRepository.findByIdPerson(idPerson);
     * Map<UUID, Address> dbAddrMap = new HashMap<>();
     * for (Address a : dbAddresses) {
     * dbAddrMap.put(a.getAddressId(), a);
     * }
     * 
     * Set<UUID> keepAddrIds = new HashSet<>();
     * List<AddressDTO> addressesDto = new ArrayList<>();
     * 
     * if (request.getAddresses() != null) {
     * for (Address ar : request.getAddresses()) {
     * 
     * // Asegurá el idPerson (por si el front no lo manda bien)
     * ar.setIdPerson(idPerson);
     * 
     * Address entity;
     * 
     * UUID reqId = ar.getAddressId(); // puede venir null
     * if (reqId != null && dbAddrMap.containsKey(reqId)) {
     * // existe => update
     * entity = dbAddrMap.get(reqId);
     * entity.setUpdateAt(LocalDate.now());
     * } else {
     * // no existe o viene null => create
     * entity = new Address();
     * entity.setCreateAt(LocalDate.now());
     * entity.setUpdateAt(LocalDate.now());
     * }
     * 
     * // Copiar campos
     * entity.setIdPerson(idPerson);
     * entity.setIdAddressType(ar.getIdAddressType());
     * entity.setCity(ar.getCity());
     * entity.setCountry(ar.getCountry());
     * entity.setPostalCode(ar.getPostalCode());
     * entity.setState(ar.getState());
     * entity.setLine1(ar.getLine1());
     * entity.setLine2(ar.getLine2());
     * entity.setPrimary(ar.getPrimary());
     * entity.setReference(ar.getReference());
     * entity.setEnabled(ar.getEnabled());
     * 
     * Address saved = addressRepository.save(entity);
     * 
     * keepAddrIds.add(saved.getAddressId());
     * addressesDto.add(mapperAddresDTO(saved));
     * }
     * }
     * 
     * // borrar lo que ya no viene
     * for (Address db : dbAddresses) {
     * if (!keepAddrIds.contains(db.getAddressId())) {
     * addressRepository.delete(db);
     * }
     * }
     * 
     * 
     * // ===== PHONES =====
     * List<Phone> dbPhones = phoneRepository.findByIdPerson(idPerson);
     * Map<UUID, Phone> dbPhoneMap = new HashMap<>();
     * for (Phone p : dbPhones) {
     * dbPhoneMap.put(p.getPhoneId(), p);
     * }
     * 
     * Set<UUID> keepPhoneIds = new HashSet<>();
     * List<PhoneDTO> phonesDto = new ArrayList<>();
     * 
     * if (request.getPhones() != null) {
     * for (Phone pn : request.getPhones()) {
     * 
     * pn.setIdPerson(idPerson);
     * 
     * Phone entity;
     * UUID reqId = pn.getPhoneId();
     * 
     * if (reqId != null && dbPhoneMap.containsKey(reqId)) {
     * entity = dbPhoneMap.get(reqId);
     * entity.setUpdateAt(LocalDate.now());
     * } else {
     * entity = new Phone();
     * entity.setCreateAt(LocalDate.now());
     * entity.setUpdateAt(LocalDate.now());
     * }
     * 
     * entity.setIdPerson(idPerson);
     * entity.setIdPhoneType(pn.getIdPhoneType());
     * entity.setPhoneNumber(pn.getPhoneNumber());
     * entity.setEnabled(pn.getEnabled());
     * 
     * Phone saved = phoneRepository.save(entity);
     * 
     * keepPhoneIds.add(saved.getPhoneId());
     * phonesDto.add(mapperPhoneDTO(saved));
     * }
     * }
     * 
     * for (Phone db : dbPhones) {
     * if (!keepPhoneIds.contains(db.getPhoneId())) {
     * phoneRepository.delete(db);
     * }
     * }
     * 
     * return Optional.of(new AddressPhoneResponse(addressesDto, phonesDto));
     * }
     */
    @Transactional
    @Override
    public Optional<AddressPhoneResponse> update(UUID idPerson, AddressPhoneRequest request) {

        // ===================== ADDRESSES =====================
        List<Address> dbAddresses = addressRepository.findByIdPerson(idPerson);

        // Mapa por clave lógica: idAddressType
        Map<String, Address> dbAddrByType = new HashMap<>();
        for (Address a : dbAddresses) {
            dbAddrByType.put(a.getIdAddressType(), a);
        }

        Set<UUID> keepAddrIds = new HashSet<>();
        List<AddressDTO> addressesDto = new ArrayList<>();

        if (request.getAddresses() != null) {
            for (Address ar : request.getAddresses()) {

                ar.setIdPerson(idPerson);

                Address entity;
                Address existing = dbAddrByType.get(ar.getIdAddressType());

                if (existing != null) {
                    // UPDATE
                    entity = existing;
                    entity.setUpdateAt(LocalDate.now());
                } else {
                    // INSERT
                    entity = new Address();
                    entity.setIdPerson(idPerson);
                    entity.setIdAddressType(ar.getIdAddressType());
                    entity.setCreateAt(LocalDate.now());
                    entity.setUpdateAt(LocalDate.now());
                }

                entity.setCity(ar.getCity());
                entity.setCountry(ar.getCountry());
                entity.setPostalCode(ar.getPostalCode());
                entity.setState(ar.getState());
                entity.setLine1(ar.getLine1());
                entity.setLine2(ar.getLine2());
                entity.setPrimary(ar.getPrimary());
                entity.setReference(ar.getReference());
                entity.setEnabled(ar.getEnabled());

                Address saved = addressRepository.save(entity);

                keepAddrIds.add(saved.getAddressId());
                addressesDto.add(mapperAddresDTO(saved));
            }
        }

        // DELETE: lo que ya no viene en el request
        for (Address db : dbAddresses) {
            if (!keepAddrIds.contains(db.getAddressId())) {
                addressRepository.delete(db);
            }
        }

        // ===================== PHONES =====================
        List<Phone> dbPhones = phoneRepository.findByIdPerson(idPerson);
        Map<UUID, Phone> dbPhoneMap = new HashMap<>();
        for (Phone p : dbPhones) {
            dbPhoneMap.put(p.getPhoneId(), p);
        }

        Set<UUID> keepPhoneIds = new HashSet<>();
        List<PhoneDTO> phonesDto = new ArrayList<>();

        if (request.getPhones() != null) {
            for (Phone pn : request.getPhones()) {

                pn.setIdPerson(idPerson);

                Phone entity;
                UUID reqId = pn.getPhoneId();

                if (reqId != null && dbPhoneMap.containsKey(reqId)) {
                    // UPDATE
                    entity = dbPhoneMap.get(reqId);
                    entity.setUpdateAt(LocalDate.now());
                } else {
                    // INSERT
                    entity = new Phone();
                    entity.setIdPerson(idPerson);
                    entity.setCreateAt(LocalDate.now());
                    entity.setUpdateAt(LocalDate.now());
                }

                entity.setIdPhoneType(pn.getIdPhoneType());
                entity.setPhoneNumber(pn.getPhoneNumber());
                entity.setEnabled(pn.getEnabled());

                Phone saved = phoneRepository.save(entity);

                keepPhoneIds.add(saved.getPhoneId());
                phonesDto.add(mapperPhoneDTO(saved));
            }
        }

        // DELETE phones que ya no vienen
        for (Phone db : dbPhones) {
            if (!keepPhoneIds.contains(db.getPhoneId())) {
                phoneRepository.delete(db);
            }
        }

        return Optional.of(new AddressPhoneResponse(addressesDto, phonesDto));
    }

    @Transactional
    @Override
    public Optional<AddressPhoneResponse> deleteAll(UUID id) {
        AddressPhoneResponse response = new AddressPhoneResponse();

        List<AddressDTO> addresses = addressRepository.findByIdPerson(id).stream().map(this::mapperAddresDTO).toList();
        List<PhoneDTO> phones = phoneRepository.findByIdPerson(id).stream().map(this::mapperPhoneDTO).toList();

        if (addresses.isEmpty() && phones.isEmpty()) {
            return Optional.empty();
        }

        addressRepository.deleteByIdPerson(id);
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
            List<AddressDTO> addresses = addressRepository.findByIdPerson(id).stream().map(this::mapperAddresDTO)
                    .toList();
            addressRepository.deleteByIdPerson(id);
            return addresses;
        }
        return null;
    }

    @Transactional
    @Override
    public List<PhoneDTO> deleteAllPhones(UUID id) {
        Optional<Phone> phone = phoneRepository.findFirstByIdPerson(id);
        if (phone.isPresent()) {
            List<PhoneDTO> phones = phoneRepository.findByIdPerson(id).stream().map(this::mapperPhoneDTO).toList();
            addressRepository.deleteByIdPerson(id);
            return phones;
        }
        return null;
    }

    @Transactional
    @Override
    public Optional<AddressDTO> saveAddress(Address request) {
        String uri = "/address-type";
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
            addressRepository.deleteByIdPerson(id);
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
            Optional<Phone> error = phoneRepository.findByPhoneNumber(request.getPhoneNumber());
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
        String uri = "/address-type";
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

        System.out.println("http://MSVC-CATALOG" + uri + "/{id}");
        System.out.println(params.get("id"));

        return Optional.ofNullable(webClientBuilder.build()
                .get()
                .uri("http://MSVC-CATALOG" + uri + "/{id}", params)
                .retrieve()
                .bodyToMono(responseType)
                .block());
    }
}
