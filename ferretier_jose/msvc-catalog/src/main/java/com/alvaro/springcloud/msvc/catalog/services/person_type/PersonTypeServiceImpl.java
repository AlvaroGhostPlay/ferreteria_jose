package com.alvaro.springcloud.msvc.catalog.services.person_type;

import com.alvaro.springcloud.msvc.catalog.entities.PersonType;
import com.alvaro.springcloud.msvc.catalog.repositories.PersonTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PersonTypeServiceImpl implements PersonTypeService{

    @Autowired
    private PersonTypeRepository personTypeRepository;

    @Transactional(readOnly = true)
    @Override
    public List<PersonType> getAllPersonTypes() {
        return personTypeRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Page<PersonType> getAllPersonTypesPage(Pageable pageable) {
        return personTypeRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<PersonType> getPersonById(String id) {
        return personTypeRepository.findByPersonTypeId(id);
    }

    @Transactional
    @Override
    public Optional<PersonType> save(PersonType request) {
        PersonType gener = new PersonType();
        gener.setPersonType(request.getPersonType());
        gener.setPersonTypeId(request.getPersonTypeId());
        personTypeRepository.save(gener);
        return Optional.of(gener);
    }

    @Transactional
    @Override
    public Optional<PersonType> update(PersonType request, String id) {
        Optional<PersonType> gener = personTypeRepository.findByPersonTypeId(id);
        if (gener.isPresent()) {
            gener.get().setPersonType(request.getPersonType());
            gener.get().setPersonTypeId(request.getPersonTypeId());
            return gener;
        }
        return Optional.empty();
    }

    @Transactional
    @Override
    public Optional<PersonType> delete(String id) {
        Optional<PersonType> addOptional = personTypeRepository.findByPersonTypeId(id);
        if (addOptional.isPresent()) {
            return personTypeRepository.deleteByPersonTypeId(id);
        }
        return Optional.empty();
    }
}
