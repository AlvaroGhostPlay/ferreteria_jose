package com.alvaro.springcloud.msvc.navigation.provider.controllers;

import com.alvaro.springcloud.msvc.navigation.provider.dto.response.MenuResponseDto;
import com.alvaro.springcloud.msvc.navigation.provider.entities.Menu;
import com.alvaro.springcloud.msvc.navigation.provider.entities.MenuItem;
import com.alvaro.springcloud.msvc.navigation.provider.services.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/menus")
public class MenuController {

    @Autowired
    private MenuService menuService;

    // ===== CRUD Menu =====

    @GetMapping("/{menuId}")
    public ResponseEntity<?> getMenus(@PathVariable UUID menuId) {
        Optional<MenuResponseDto> menuOp = menuService.getMenu( menuId);
        return menuOp.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/whith-itmes/{menuId}")
    public ResponseEntity<?> getMenusWhitItems(@PathVariable UUID menuId) {
        List<MenuResponseDto> menuOp = menuService.getMenusWhitItems( menuId);
        return ResponseEntity.ok().body(menuOp);
    }

    @GetMapping("/whith-itmes")
    public ResponseEntity<?> getMenusWhitItems() {
        List<MenuResponseDto> menuOp = menuService.getMenusWhitItems();
        return ResponseEntity.ok().body(menuOp);
    }

    @GetMapping
    public ResponseEntity<?> list() {
        return ResponseEntity.ok().body(menuService.listMenus());
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Menu menu) {
        Optional<Menu> menuOp = menuService.createMenu( menu);
        return menuOp.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.noContent().build());
    }

    @PutMapping("/{menuId}")
    public ResponseEntity<?> update(@PathVariable UUID menuId, @RequestBody Menu menu) {
        Optional<Menu> menuOp = menuService.updateMenu( menuId, menu);
        return menuOp.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{menuId}")
    public ResponseEntity<?> delete(@PathVariable UUID menuId) {
        Optional<Menu> menuOp = menuService.deleteMenu( menuId);
        return menuOp.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // ===== Items =====

    @PostMapping("/items")
    public ResponseEntity<?> addItem( @RequestBody MenuItem item) {
        Optional<MenuResponseDto.MenuItemDto> menuItemOp = menuService.addItem( item);
        return menuItemOp.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/items/{itemId}")
    public ResponseEntity<?> deleteItem(@PathVariable UUID itemId) {
        Optional<MenuResponseDto.MenuItemDto> menuItemOp = menuService.deleteItem( itemId);
        return menuItemOp.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // ===== Menus por rol =====

    @GetMapping("/by-role/{roleId}")
    public List<MenuResponseDto> byRole(@PathVariable UUID roleId, @RequestParam boolean isMenu) {
        return menuService.getMenusByRole(roleId, isMenu);
    }

    // ===== Menus por trabajador (consulta otro MSVC para resolver role) =====
    @GetMapping("/by-worker/{workerId}")
    public List<MenuResponseDto> byWorker(@PathVariable UUID workerId, @RequestParam Boolean isMenu) {
        return menuService.getMenusByWorker(workerId, isMenu);
    }

    // ===== Asignar/Desasignar Menu <-> Role =====

    @PostMapping("/{menuId}/roles/{roleId}")
    public ResponseEntity<Void> assign(@PathVariable UUID menuId, @PathVariable UUID roleId) {
        menuService.assignMenuToRole(menuId, roleId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{menuId}/roles/{roleId}")
    public ResponseEntity<Void> unassign(@PathVariable UUID menuId, @PathVariable UUID roleId) {
        menuService.unassignMenuFromRole(menuId, roleId);
        return ResponseEntity.noContent().build();
    }
}
