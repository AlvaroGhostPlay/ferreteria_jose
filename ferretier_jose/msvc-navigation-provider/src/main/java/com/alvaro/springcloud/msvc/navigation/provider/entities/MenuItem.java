package com.alvaro.springcloud.msvc.navigation.provider.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Table(name = "menus_items", schema = "ferreteria")
public class MenuItem {

    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(name = "menu_item_id", nullable = false, updatable = false)
    private UUID menuItemId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_menu", nullable = false)
    private Menu menu;

    @Column(name = "label", length = 100, nullable = false)
    private String label;

    @Column(name = "route", length = 100, nullable = false)
    private String route;

    @Column(name = "section", length = 100, nullable = false)
    private String section;

    public UUID getMenuItemId() {
        return menuItemId;
    }

    public void setMenuItemId(UUID menuItemId) {
        this.menuItemId = menuItemId;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }
}