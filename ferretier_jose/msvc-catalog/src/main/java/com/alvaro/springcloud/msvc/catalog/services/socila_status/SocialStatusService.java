package com.alvaro.springcloud.msvc.catalog.services.socila_status;

import com.alvaro.springcloud.msvc.catalog.entities.SocialStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface SocialStatusService {

    List<SocialStatus> getAllSocilaStatus();
    Page<SocialStatus> getAllSocialStatusPage(Pageable pageable);
    Optional<SocialStatus> getSocialStatusById(String id);
    Optional<SocialStatus> save(SocialStatus request);
    Optional<SocialStatus> update(SocialStatus request, String id);
    Optional<SocialStatus> delete(String id);
}
