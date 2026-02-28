package com.alvaro.springcloud.msvc.navigation.provider.services;

import com.alvaro.springcloud.msvc.navigation.provider.dto.response.MenuResponseDto;
import com.alvaro.springcloud.msvc.navigation.provider.entities.Menu;
import com.alvaro.springcloud.msvc.navigation.provider.entities.MenuItem;
import com.alvaro.springcloud.msvc.navigation.provider.entities.MenuJob;
import com.alvaro.springcloud.msvc.navigation.provider.repositories.MenuItemRepository;
import com.alvaro.springcloud.msvc.navigation.provider.repositories.MenuJobRepository;
import com.alvaro.springcloud.msvc.navigation.provider.repositories.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private MenuItemRepository menuItemRepository;

    @Autowired
    private MenuJobRepository menuJobRepository;

    @Autowired
    private  WebClient.Builder rolesWebClient;

    @Autowired
    private MenuMapper menuMapper;


    @Transactional(readOnly = true)
    @Override
    public Optional<MenuResponseDto> getMenu(UUID menuId) {
        Optional<Menu> m = menuRepository.findWithItemsByMenuId(menuId);
        if (m.isPresent()) {
            return m.map(menuMapper::toMenuResponseDto);
        }
        return Optional.empty();
    }

    @Transactional(readOnly = true)
    @Override
    public List<MenuResponseDto> listMenus() {
        return menuRepository.findAll().stream().map(menuMapper::toMenuResponseDto).toList();
    }

    @Transactional(readOnly = true)
    @Override
    public Page<MenuResponseDto> pageMenus(Pageable pageable) {
        return menuRepository.findAll(pageable).map(menuMapper::toMenuResponseDto);
    }

    @Transactional
    @Override
    public Optional<Menu> createMenu(Menu menu) {
        menu.setMenuId(null);
        return Optional.of(menuRepository.save(menu));
    }

    @Transactional
    @Override
    public Optional<Menu> updateMenu(UUID menuId, Menu menu) {
        Optional<Menu> db = menuRepository.findById(menuId);
        if (db.isPresent()) {
            db.get().setTitle(menu.getTitle());
            db.get().setSection(menu.getSection());
            db.get().setUrlBase(menu.getUrlBase());
            return db;
        }
        return Optional.empty();
    }

    @Transactional
    @Override
    public Optional<Menu> deleteMenu(UUID menuId) {
        Optional<Menu> db = menuRepository.findById(menuId);
        if (db.isPresent()) {
            menuRepository.delete(db.get());
            return db;
        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<MenuResponseDto.MenuItemDto> addItem(MenuItem item) {
        Optional<Menu> menu = menuRepository.findById(item.getMenu().getMenuId());

        if (menu.isPresent()) {
            item.setMenuItemId(null);
            item.setMenu(menu.get());
            menuItemRepository.save(item);
            return Optional.of(menuMapper.toMeniItemDto(item));
        }
        return Optional.empty();
    }

    @Transactional
    @Override
    public Optional<MenuResponseDto.MenuItemDto> deleteItem(UUID itemId) {
        Optional<MenuItem> menuItem = menuItemRepository.findById(itemId);

        if (menuItem.isPresent()) {
            menuItemRepository.deleteById(itemId);
            return Optional.of(menuMapper.toMeniItemDto(menuItem.get()));
        }
        return Optional.empty();
    }

    @Transactional
    @Override
    public Optional<MenuResponseDto.MenuItemDto> updateItem(UUID itemId, MenuItem item) {
        Optional<MenuItem> menuItem = menuItemRepository.findById(itemId);

        if (menuItem.isPresent()) {
            menuItem.get().setLabel(item.getLabel());
            menuItem.get().setRoute(item.getRoute());
            menuItem.get().setSection(item.getSection());
            return Optional.of(menuMapper.toMeniItemDto(menuItem.get()));
        }
        return Optional.empty();
    }

    @Transactional(readOnly = true)
    @Override
    public List<MenuResponseDto> getMenusWhitItems(UUID menuId) {
        return menuRepository.findWithItemsByMenuId(menuId).stream().map(menuMapper::toMenuWhitItemsResponseDto).toList();
    }

    @Transactional(readOnly = true)
    @Override
    public List<MenuResponseDto> getMenusWhitItems() {
        return menuRepository.findAll().stream().map(menuMapper::toMenuWhitItemsResponseDto).toList();
    }

    //===========================roles para traer todo==================
    @Override
    @Transactional
    public void assignMenuToRole(UUID menuId, UUID roleId) {
        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Menu no existe"));

        MenuJob menuJob = new MenuJob();
        menuJob.setMenu(menu);
        menuJob.setJobRoleId(roleId);
        MenuJob.MenuJobId id = new MenuJob.MenuJobId();
        id.setMenuId(menuId);
        id.setJobRoleId(roleId);
        menuJob.setId(id);
        menuJobRepository.save(menuJob);

        // Opcional: validar que el role exista consultando otro MSVC
        //ensureRoleExists(roleId);

        //MenuJob.MenuJobId id = new MenuJob.MenuJobId(menuId, roleId);
        //if (menuJobRepository.existsById(id)) return;

        /*MenuJob mj = MenuJob.builder()
                .id(id)
                .menu(menu)
                .jobRoleId(roleId)
                .build();*/

        //menuJobRepository.save(mj);
    }

    @Transactional
    @Override
    public void unassignMenuFromRole(UUID menuId, UUID roleId) {
        menuJobRepository.deleteById_MenuIdAndId_JobRoleId(menuId, roleId);
    }

    @Transactional(readOnly = true)
    @Override
    public List<MenuResponseDto> getMenusByRole(UUID roleId, Boolean isMenu) {
        // trae menus + items
        List<Menu> menus = menuRepository.findMenusByRoleWithItems(roleId, isMenu);
        return menus.stream().map(this::toDto).toList();
    }

    @Override
    public List<MenuResponseDto> getMenusByWorker(UUID workerId, Boolean isMenu) {
        // Ejemplo: consulto al MSVC-SECURITY/WORKERS para traer roleId
        /*UUID roleId = rolesWebClient
                .build()
                .get()
                .uri("/workers/{id}/role-id", workerId)  // <-- ajustá endpoint real
                .retrieve()
                .bodyToMono(UUID.class)
                .block();

        if (roleId == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se pudo resolver role del trabajador");
        }*/

        return getMenusByRole(workerId, isMenu);
    }

    private MenuResponseDto toDto(Menu m) {
        var items = (m.getItems() == null ? List.<MenuResponseDto.MenuItemDto>of()
                : m.getItems().stream()
                .map(i -> new MenuResponseDto.MenuItemDto(
                        i.getMenuItemId(),
                        i.getLabel(),
                        i.getRoute(),
                        i.getSection()
                ))
                .toList());

        return new MenuResponseDto(
                m.getMenuId(),
                m.getTitle(),
                m.getSection(),
                m.getUrlBase(),
                items
        );
    }

    private void ensureRoleExists(UUID roleId) {
        try {
            Boolean exists = rolesWebClient
                    .build()
                    .get()
                    .uri("/job-roles/{id}/exists", roleId) // <-- ajustá endpoint real
                    .retrieve()
                    .bodyToMono(Boolean.class)
                    .block();

            if (exists == null || !exists) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "JobRole no existe: " + roleId);
            }
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, "No se pudo validar el role en otro MSVC");
        }
    }
}
