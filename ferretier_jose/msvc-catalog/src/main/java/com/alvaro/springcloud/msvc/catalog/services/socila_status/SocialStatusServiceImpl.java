package com.alvaro.springcloud.msvc.catalog.services.socila_status;

import com.alvaro.springcloud.msvc.catalog.entities.SocialStatus;
import com.alvaro.springcloud.msvc.catalog.repositories.SocialStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class SocialStatusServiceImpl implements SocialStatusService {

    @Autowired
    private SocialStatusRepository socialStatusRepository;

    @Transactional(readOnly = true)
    @Override
    public List<SocialStatus> getAllSocilaStatus() {
        return socialStatusRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Page<SocialStatus> getAllSocialStatusPage(Pageable pageable) {
        return socialStatusRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<SocialStatus> getSocialStatusById(String id) {
        return socialStatusRepository.findBySocialStatusId(id);
    }

    @Transactional
    @Override
    public Optional<SocialStatus> save(SocialStatus request) {
        SocialStatus socialStatus = new SocialStatus();
        socialStatus.setSocialStatus(request.getSocialStatus());
        socialStatus.setSocialStatusId(request.getSocialStatusId());
        socialStatusRepository.save(socialStatus);
        return Optional.of(socialStatus);
    }

    @Transactional
    @Override
    public Optional<SocialStatus> update(SocialStatus request, String id) {
        Optional<SocialStatus> socialStatus = socialStatusRepository.findBySocialStatusId(id);
        if (socialStatus.isPresent()) {
            socialStatus.get().setSocialStatusId(request.getSocialStatusId());
            socialStatus.get().setSocialStatus(request.getSocialStatus());
            return socialStatus;
        }
        return Optional.empty();
    }

    @Transactional
    @Override
    public Optional<SocialStatus> delete(String id) {
        Optional<SocialStatus> addOptional = socialStatusRepository.findBySocialStatusId(id);
        if (addOptional.isPresent()) {
            return socialStatusRepository.deleteBySocialStatusId(id);
        }
        return Optional.empty();
    }
}
