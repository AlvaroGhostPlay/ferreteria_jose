package com.alvaro.springcloud.msvc.users.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.bouncycastle.pqc.jcajce.provider.SABER;

import java.util.UUID;

@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "role_id", updatable = false, nullable = false, columnDefinition = "uuid")
    private UUID roleId;

    @NotBlank
    @Column(name = "role")
    private String roleName;

    public UUID getRoleId() {
        return roleId;
    }

    public void setRoleId(UUID roleId) {
        this.roleId = roleId;
    }

    public @NotBlank String getRoleName() {
        return roleName;
    }

    public void setRoleName(@NotBlank String roleName) {
        this.roleName = roleName;
    }
}
