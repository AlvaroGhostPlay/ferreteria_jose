package com.alvaro.springcloud.msvc.catalog.services.address_type;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.alvaro.springcloud.msvc.catalog.entities.AddressType;
import com.alvaro.springcloud.msvc.catalog.repositories.AddressTypeRepository;

import org.springframework.transaction.annotation.Transactional;

@Service
public class AddressTypeServiceImpl implements AddressTypeService {

    @Autowired
    private AddressTypeRepository addressTypeRepository;

    @Transactional(readOnly = true)
    @Override
    public Optional<AddressType> getAddresTypeById(String id) {
        return addressTypeRepository.findByAddressTypeId(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<AddressType> getAllAddresType() {
        return addressTypeRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Page<AddressType> getAllAddresTypePage(Pageable pageable) {
        return addressTypeRepository.findAll(pageable);
    }

    @Transactional
    @Override
    public Optional<AddressType> save(AddressType request) {
        AddressType addressType = new AddressType();
        addressType.setAddressType(request.getAddressType());
        addressType.setAddressTypeId(request.getAddressTypeId());
        addressTypeRepository.save(addressType);
        return Optional.of(addressType);
    }

    @Transactional
    @Override
    public Optional<AddressType> update(AddressType request, String id) {
        Optional<AddressType> addOptional = addressTypeRepository.findByAddressTypeId(id);
        if (addOptional.isPresent()) {
            addOptional.get().setAddressType(request.getAddressType());
            addOptional.get().setAddressTypeId(request.getAddressTypeId());
            return addOptional;
        }
        return Optional.empty();
    }   

    @Transactional
    @Override
    public Optional<AddressType> delete(String id) {
        Optional<AddressType> addOptional = addressTypeRepository.findByAddressTypeId(id);
        if (addOptional.isPresent()) {
            return addressTypeRepository.deleteByAddressTypeId(id);
        }
        return Optional.empty();
    }
}
