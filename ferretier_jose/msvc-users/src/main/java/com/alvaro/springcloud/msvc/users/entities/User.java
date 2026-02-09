package com.alvaro.springcloud.msvc.users.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "user_id", updatable = false, nullable = false, columnDefinition = "uuid")
    private UUID userId;

    @NotBlank
    @Column(unique = true)
    private String username;

    @NotBlank
    private String password;

    private Boolean enabled;

    @Column(name = "most_change_pass")
    private Boolean mostChangePass;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "pass_update_at")
    private LocalDateTime passUpdateAt;

    @NotNull
    @JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "id_user", referencedColumnName = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "id_role", referencedColumnName = "role_id"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"id_user", "id_role"})
    )
    private Set<Role> roles;

    public User() {
        roles = new HashSet<>();
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

    public LocalDateTime getPassUpdateAt() {
        return passUpdateAt;
    }

    public void setPassUpdateAt(LocalDateTime passUpdateAt) {
        this.passUpdateAt = passUpdateAt;
    }
}
