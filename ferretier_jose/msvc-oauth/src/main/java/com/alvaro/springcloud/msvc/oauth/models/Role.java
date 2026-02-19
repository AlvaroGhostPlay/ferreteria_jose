package com.alvaro.springcloud.msvc.oauth.models;

import java.util.UUID;

public class Role {
    private UUID roleId;
    private String roleName;

    public UUID getRoleId() {
        return roleId;
    }

    public void setRoleId(UUID id) {
        this.roleId = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String name) {
        this.roleName = name;
    }
}
