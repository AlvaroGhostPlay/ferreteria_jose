package com.alvaro.springcloud.msvc.users.dto.response;

import com.alvaro.springcloud.msvc.users.entities.Role;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class UserResponseDTO {

    private UUID userId;
    private String username;
    private Boolean enabled;
    private Boolean mostChangePass;
    private LocalDateTime createdAt;
    private LocalDateTime passUpdateAt;
    private Set<Role> roles;

    public UserResponseDTO() {
        roles = new HashSet<>();
    }

    public UserResponseDTO(
            UUID userId,
            String username,
            Boolean enabled,
            Boolean mostChangePass,
            LocalDateTime createdAt,
            LocalDateTime passUpdateAt,
            Set<Role> roles
    ) {
        this.userId = userId;
        this.username = username;
        this.enabled = enabled;
        this.mostChangePass = mostChangePass;
        this.createdAt = createdAt;
        this.passUpdateAt = passUpdateAt;
        this.roles = roles;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public Boolean getMostChangePass() {
        return mostChangePass;
    }

    public void setMostChangePass(Boolean mostChangePass) {
        this.mostChangePass = mostChangePass;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public LocalDateTime getPassUpdateAt() {
        return passUpdateAt;
    }

    public void setPassUpdateAt(LocalDateTime passUpdateAt) {
        this.passUpdateAt = passUpdateAt;
    }
}
