package com.alvaro.springcloud.msvc.catalog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alvaro.springcloud.msvc.catalog.entities.Gener;

import java.util.Optional;

public interface GenerRepository extends JpaRepository<Gener, String>{
    Optional<Gener> deleteByGenerId(String generId);
    Optional<Gener> findByGenerId(String generId);

}
