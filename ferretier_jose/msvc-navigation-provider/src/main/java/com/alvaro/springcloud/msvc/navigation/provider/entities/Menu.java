package com.alvaro.springcloud.msvc.navigation.provider.entities;


import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "menus", schema = "ferreteria")
public class Menu {

    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(name = "menu_id", nullable = false, updatable = false)
    private UUID menuId;

    @Column(name = "title", length = 100, nullable = false)
    private String title;

    @Column(name = "section", length = 100, nullable = false)
    private String section;

    // En tu DDL está "ulr_base" (typo). Mapeo igual para que funcione.
    @Column(name = "ulr_base", length = 100, nullable = false)
    private String urlBase;

    @Column(name = "is_menu", length = 100, nullable = false)
    private Boolean isMenu;

    @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MenuItem> items = new ArrayList<>();

    public UUID getMenuId() {
        return menuId;
    }

    public void setMenuId(UUID menuId) {
        this.menuId = menuId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getUrlBase() {
        return urlBase;
    }

    public void setUrlBase(String urlBase) {
        this.urlBase = urlBase;
    }

    public List<MenuItem> getItems() {
        return items;
    }

    public void setItems(List<MenuItem> items) {
        this.items = items;
    }

    public Boolean getIsMenu() {
        return isMenu;
    }

    public void setIsMenu(Boolean isMenu) {
        this.isMenu = isMenu;
    }

    public Menu addMenItem(MenuItem item) {
        this.items.add(item);
        item.setMenu(this);
        return this;
    }

    public Menu removeMenItem(MenuItem item) {
        this.items.remove(item);
        item.setMenu(null);
        return this;
    }
}
