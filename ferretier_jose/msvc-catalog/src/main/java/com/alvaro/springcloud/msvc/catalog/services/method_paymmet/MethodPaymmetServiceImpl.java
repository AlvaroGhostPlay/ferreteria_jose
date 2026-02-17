package com.alvaro.springcloud.msvc.catalog.services.method_paymmet;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.alvaro.springcloud.msvc.catalog.entities.MethodPaymment;
import com.alvaro.springcloud.msvc.catalog.repositories.MethodPaymmetRepository;

import org.springframework.transaction.annotation.Transactional;

@Service
public class MethodPaymmetServiceImpl implements MethodPaymmetService {

    @Autowired
    private MethodPaymmetRepository methodPaymmetRepository;

    @Transactional(readOnly = true)
    @Override
    public Optional<MethodPaymment> getMethodPaymmetById(String id) {
        return methodPaymmetRepository.findByMethodPaymmentId(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<MethodPaymment> getAllMethodPayments() {
        return methodPaymmetRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Page<MethodPaymment> getAllMethodPaymmetPage(Pageable pageable) {
        return methodPaymmetRepository.findAll(pageable);
    }

    @Transactional
    @Override
    public Optional<MethodPaymment> save(MethodPaymment request) {
        MethodPaymment methodPaymmet = new MethodPaymment();
        methodPaymmet.setMethodPaymment(request.getMethodPaymment());
        methodPaymmet.setMethodPaymmentId(request.getMethodPaymmentId());
        methodPaymmetRepository.save(methodPaymmet);
        return Optional.of(methodPaymmet);
    }

    @Transactional
    @Override
    public Optional<MethodPaymment> update(MethodPaymment request, String id) {
        Optional<MethodPaymment> addOptional = methodPaymmetRepository.findByMethodPaymmentId(id);
        if (addOptional.isPresent()) {
            addOptional.get().setMethodPaymment(request.getMethodPaymment());
            addOptional.get().setMethodPaymmentId(request.getMethodPaymmentId());
            return addOptional;
        }
        return Optional.empty();
    }   

    @Transactional
    @Override
    public Optional<MethodPaymment> delete(String id) {
        Optional<MethodPaymment> addOptional = methodPaymmetRepository.findByMethodPaymmentId(id);
        if (addOptional.isPresent()) {
            return methodPaymmetRepository.deleteByMethodPaymmentId(id);
        }
        return Optional.empty();
    }
}
