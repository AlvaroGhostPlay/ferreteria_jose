package com.alvaro.springcloud.msvc.navigation.provider.dto.response;

import java.util.List;
import java.util.UUID;

public record MenuResponseDto(
        UUID menuId,
        String title,
        String section,
        String urlBase,
        List<MenuItemDto> items
) {
    public record MenuItemDto(UUID menuItemId, String label, String route, String section) {}
}
