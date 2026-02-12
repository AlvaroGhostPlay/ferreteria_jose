package com.alvaro.springcloud.msvc.info.person.repositories;

import com.alvaro.springcloud.msvc.info.person.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AddressRepository extends JpaRepository<Address, UUID> {
    List<Address> findByIdPerson(UUID personId);
    Optional<Address> findFirstByIdPerson(UUID personId);
    Optional<Address> findByAddressId(UUID AddresId);
    Optional<Address> findByIdPersonAndIdAddressType(UUID personId, String idAddressType);
    void deleteByIdPerson(UUID personId);
}
