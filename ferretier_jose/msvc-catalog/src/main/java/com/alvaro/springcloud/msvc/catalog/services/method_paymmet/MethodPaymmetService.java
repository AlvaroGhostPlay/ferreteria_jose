package com.alvaro.springcloud.msvc.catalog.services.method_paymmet;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.alvaro.springcloud.msvc.catalog.entities.MethodPaymment;

public interface MethodPaymmetService {   
    List<MethodPaymment> getAllMethodPayments();
    Page<MethodPaymment> getAllMethodPaymmetPage(Pageable pageable);
    Optional<MethodPaymment> getMethodPaymmetById(String id);
    Optional<MethodPaymment> save(MethodPaymment request);
    Optional<MethodPaymment> update(MethodPaymment request, String id);
    Optional<MethodPaymment> delete(String id);
}