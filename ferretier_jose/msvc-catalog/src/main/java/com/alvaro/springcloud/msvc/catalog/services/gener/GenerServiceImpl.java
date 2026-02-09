package com.alvaro.springcloud.msvc.catalog.services.gener;

import com.alvaro.springcloud.msvc.catalog.entities.Gener;
import com.alvaro.springcloud.msvc.catalog.repositories.GenerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class GenerServiceImpl implements GenerService{

    @Autowired
    private GenerRepository generRepository;

    @Transactional(readOnly = true)
    @Override
    public Optional<Gener> getGenerById(String id) {
        return generRepository.findByGenerId(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Gener> getAllGeners() {
        return generRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Gener> getAllGenersPage(Pageable pageable) {
        return generRepository.findAll(pageable);
    }

    @Transactional
    @Override
    public Optional<Gener> save(Gener request) {
        Gener gener = new Gener();
        gener.setGener(request.getGener());
        gener.setGenerId(request.getGenerId());
        generRepository.save(gener);
        return Optional.of(gener);
    }

    @Transactional
    @Override
    public Optional<Gener> update(Gener request, String id) {
        Optional<Gener> gener = generRepository.findByGenerId(id);
        if (gener.isPresent()) {
            gener.get().setGener(request.getGener());
            gener.get().setGenerId(id);
            return gener;
        }
        return Optional.empty();
    }

    @Transactional
    @Override
    public Optional<Gener> delete(String id) {
        Optional<Gener> addOptional = generRepository.findByGenerId(id);
        if (addOptional.isPresent()) {
            return generRepository.deleteByGenerId(id);
        }
        return Optional.empty();
    }
}
