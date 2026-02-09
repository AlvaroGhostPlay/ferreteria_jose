package com.alvaro.springcloud.msvc.warehouses.entities;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.UUID;
import java.util.Objects;

@Embeddable
public class UserJobRoleWarehouseId implements Serializable {

    private UUID idUser;
    private UUID idJobRole;
    private UUID idWarehouse;

    public UserJobRoleWarehouseId() {}

    public UserJobRoleWarehouseId(UUID idUser, UUID idJobRole, UUID idWarehouse) {
        this.idUser = idUser;
        this.idJobRole = idJobRole;
        this.idWarehouse = idWarehouse;
    }

    public UUID getIdUser() {
        return idUser;
    }

    public void setIdUser(UUID idUser) {
        this.idUser = idUser;
    }

    public UUID getIdJobRole() {
        return idJobRole;
    }

    public void setIdJobRole(UUID idJobRole) {
        this.idJobRole = idJobRole;
    }

    public UUID getIdWarehouse() {
        return idWarehouse;
    }

    public void setIdWarehouse(UUID idWarehouse) {
        this.idWarehouse = idWarehouse;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserJobRoleWarehouseId that = (UserJobRoleWarehouseId) o;
        return Objects.equals(idUser, that.idUser)
                && Objects.equals(idJobRole, that.idJobRole)
                && Objects.equals(idWarehouse, that.idWarehouse);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUser, idJobRole, idWarehouse);
    }
}