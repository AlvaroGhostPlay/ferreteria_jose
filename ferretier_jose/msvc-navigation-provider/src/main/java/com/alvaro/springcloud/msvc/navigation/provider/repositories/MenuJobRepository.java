package com.alvaro.springcloud.msvc.navigation.provider.repositories;

import com.alvaro.springcloud.msvc.navigation.provider.entities.MenuJob;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface MenuJobRepository extends JpaRepository<MenuJob, MenuJob.MenuJobId> {
    List<MenuJob> findById_JobRoleId(UUID roleId);
    void deleteById_MenuIdAndId_JobRoleId(UUID menuId, UUID roleId);
}
