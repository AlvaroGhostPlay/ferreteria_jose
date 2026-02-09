package com.alvaro.springcloud.msvc.catalog.repositories;

import com.alvaro.springcloud.msvc.catalog.entities.PhoneType;
import org.springframework.data.jpa.repository.JpaRepository;

import com.alvaro.springcloud.msvc.catalog.entities.SocialStatus;

import java.util.Optional;

public interface SocialStatusRepository extends JpaRepository<SocialStatus, String>{
    Optional<SocialStatus> findBySocialStatusId(String socialStatusId);
    Optional<SocialStatus> deleteBySocialStatusId(String socialStatusId);
    
}
