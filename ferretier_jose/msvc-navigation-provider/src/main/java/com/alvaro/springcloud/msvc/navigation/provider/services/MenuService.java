package com.alvaro.springcloud.msvc.navigation.provider.services;

import com.alvaro.springcloud.msvc.navigation.provider.dto.response.MenuResponseDto;
import com.alvaro.springcloud.msvc.navigation.provider.entities.Menu;
import com.alvaro.springcloud.msvc.navigation.provider.entities.MenuItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MenuService {

    // CRUD Menu
    Optional<Menu> createMenu(Menu menu);
    Optional<Menu> updateMenu(UUID menuId, Menu menu);
    Optional<Menu> deleteMenu(UUID menuId);
    Optional<MenuResponseDto> getMenu(UUID menuId);
    List<MenuResponseDto> listMenus();
    List<MenuResponseDto> getMenusWhitItems();
    Page<MenuResponseDto> pageMenus(Pageable pageable);

    // Items
    Optional<MenuResponseDto.MenuItemDto> addItem(MenuItem item);
    Optional<MenuResponseDto.MenuItemDto> deleteItem(UUID itemId);
    Optional<MenuResponseDto.MenuItemDto> updateItem(UUID itemId, MenuItem item);
    List<MenuResponseDto> getMenusWhitItems(UUID menuId);

    // Asignaciones menu-role
    void assignMenuToRole(UUID menuId, UUID roleId);
    void unassignMenuFromRole(UUID menuId, UUID roleId);

    // Query por rol
    List<MenuResponseDto> getMenusByRole(UUID roleId, Boolean isMenu);

    // Ejemplo: si querés que el MSVC reciba "workerId" y consulte otro MSVC para sacar roleId
    List<MenuResponseDto> getMenusByWorker(UUID workerId, Boolean isMenu);
}
