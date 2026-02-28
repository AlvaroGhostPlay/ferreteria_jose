package com.alvaro.springcloud.msvc.navigation.provider.repositories;


import com.alvaro.springcloud.msvc.navigation.provider.entities.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface MenuItemRepository extends JpaRepository<MenuItem, UUID> {
    List<MenuItem> findByMenu_MenuId(UUID menuId);
}