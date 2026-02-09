package com.alvaro.springcloud.msvc.catalog.services.phone_type;

import com.alvaro.springcloud.msvc.catalog.entities.PhoneType;
import com.alvaro.springcloud.msvc.catalog.repositories.PhoneTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PhoneTypeServiceImpl implements PhoneTypeService{

    @Autowired
    private PhoneTypeRepository phoneTypeRepository;

    @Transactional(readOnly = true)
    @Override
    public List<PhoneType> getAllPhonesTypes() {
        return phoneTypeRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Page<PhoneType> getAllPhonesTypesPage(Pageable pageable) {
        return phoneTypeRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<PhoneType> getPhonesById(String id) {
        return phoneTypeRepository.findByPhoneTypeId(id);
    }

    @Transactional
    @Override
    public Optional<PhoneType> save(PhoneType request) {
        PhoneType phoneType = new PhoneType();
        phoneType.setPhoneType(request.getPhoneType());
        phoneType.setPhoneTypeId(request.getPhoneTypeId());
        phoneTypeRepository.save(phoneType);
        return Optional.of(phoneType);
    }

    @Transactional
    @Override
    public Optional<PhoneType> update(PhoneType request, String id) {
        Optional<PhoneType> phoneType = phoneTypeRepository.findByPhoneTypeId(id);
        if (phoneType.isPresent()) {
            phoneType.get().setPhoneTypeId(request.getPhoneTypeId());
            phoneType.get().setPhoneType(request.getPhoneType());
            return phoneType;
        }
        return Optional.empty();
    }

    @Transactional
    @Override
    public Optional<PhoneType> delete(String id) {
        Optional<PhoneType> addOptional = phoneTypeRepository.findByPhoneTypeId(id);
        if (addOptional.isPresent()) {
            return phoneTypeRepository.deleteByPhoneTypeId(id);
        }
        return Optional.empty();
    }
}
