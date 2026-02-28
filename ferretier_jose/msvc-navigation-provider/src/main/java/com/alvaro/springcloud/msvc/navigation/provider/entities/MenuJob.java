package com.alvaro.springcloud.msvc.navigation.provider.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "menus_jobs", schema = "ferreteria")
public class MenuJob {

    @EmbeddedId
    private MenuJobId id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("menuId")
    @JoinColumn(name = "id_menu", nullable = false)
    private Menu menu;

    // como jobs_roles vive en otra tabla/otro MSVC, aquí solo guardamos el UUID
    @Column(name = "id_job_role", nullable = false, insertable = false, updatable = false)
    private UUID jobRoleId;

    @Embeddable
    public static class MenuJobId implements Serializable {
        @Column(name = "id_menu", nullable = false)
        private UUID menuId;

        @Column(name = "id_job_role", nullable = false)
        private UUID jobRoleId;

        public UUID getMenuId() {
            return menuId;
        }

        public void setMenuId(UUID menuId) {
            this.menuId = menuId;
        }

        public UUID getJobRoleId() {
            return jobRoleId;
        }

        public void setJobRoleId(UUID jobRoleId) {
            this.jobRoleId = jobRoleId;
        }
    }


    public UUID getJobRoleId() {
        return jobRoleId;
    }

    public void setJobRoleId(UUID jobRoleId) {
        this.jobRoleId = jobRoleId;
    }

    public MenuJobId getId() {
        return id;
    }

    public void setId(MenuJobId id) {
        this.id = id;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }
}
