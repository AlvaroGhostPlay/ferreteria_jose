package com.alvaro.springcloud.msvc.oauth.models;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class User {
    private UUID userId;

    private String username;

    private String password;

    private Boolean enabled;

    private Set<Role> roles;

    public User() {
        roles = new HashSet<>();
    }

    public UUID getUserId() { return userId; }
    public void setUserId(UUID userId) { this.userId = userId; }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
