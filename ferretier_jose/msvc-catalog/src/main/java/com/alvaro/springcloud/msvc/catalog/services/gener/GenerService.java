package com.alvaro.springcloud.msvc.catalog.services.gener;

import com.alvaro.springcloud.msvc.catalog.entities.Gener;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface GenerService {
    List<Gener> getAllGeners();
    Page<Gener> getAllGenersPage(Pageable pageable);
    Optional<Gener> getGenerById(String id);
    Optional<Gener> save(Gener request);
    Optional<Gener> update(Gener request, String id);
    Optional<Gener> delete(String id);
}
