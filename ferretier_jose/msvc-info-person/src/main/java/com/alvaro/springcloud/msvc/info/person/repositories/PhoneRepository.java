package com.alvaro.springcloud.msvc.info.person.repositories;

import com.alvaro.springcloud.msvc.info.person.entities.Phone;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PhoneRepository extends JpaRepository<Phone, UUID> {
    List<Phone> findByIdPerson(UUID personId);
    Optional<Phone> findFirstByIdPerson(UUID personId);
    void deleteByIdPerson(UUID personId);
    void deleteByPhoneId(UUID personId);
    Optional<Phone> findByPhoneId(UUID phoneId);
    Optional<Phone> findByPhoneNumber(String phone);
    Optional<Phone> findByIdPersonAndPhoneNumber(UUID idPerson, String idPhoneType);
    void deleteByIdPersonAndPhoneIdNotIn(UUID idPerson, List<UUID> ids);
}
