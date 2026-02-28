package com.alvaro.springcloud.msvc.navigation.provider.services;

import com.alvaro.springcloud.msvc.navigation.provider.dto.response.MenuResponseDto;
import com.alvaro.springcloud.msvc.navigation.provider.entities.Menu;
import com.alvaro.springcloud.msvc.navigation.provider.entities.MenuItem;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuMapper {

    public MenuResponseDto toMenuResponseDto(Menu menu) {
        return new MenuResponseDto(
                menu.getMenuId(),
                menu.getTitle(),
                menu.getSection(),
                menu.getUrlBase(),
                null
        );
    }

    public MenuResponseDto toMenuWhitItemsResponseDto(Menu menu) {
        List<MenuItem> items = menu.getItems();
        List<MenuResponseDto.MenuItemDto> menuResponseDtos =
                menu.getItems().
                        stream()
                        .map(this::toMeniItemDto).toList();

        return new MenuResponseDto(
                menu.getMenuId(),
                menu.getTitle(),
                menu.getSection(),
                menu.getUrlBase(),
                menuResponseDtos
        );
    }

    public MenuResponseDto.MenuItemDto toMeniItemDto(MenuItem menuItem) {
        return new MenuResponseDto.MenuItemDto(
                menuItem.getMenuItemId(),
                menuItem.getLabel(),
                menuItem.getRoute(),
                menuItem.getSection()
        );
    }
}
