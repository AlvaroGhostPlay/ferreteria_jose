package com.alvaro.springcloud.msvc.info.person.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(
        name = "phones",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_person_phone_type",
                        columnNames = {"id_person", "id_phone_type"}
                )
        }
)
public class Phone {
    @Id
    @Column(name = "phone_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID phoneId;

    @NotBlank
    @Column(name = "id_person")
    private UUID idPerson;

    @NotBlank
    @Column(name = "id_person_type")
    private String idPhoneType;

    @NotBlank
    @Column(name = "phone")
    private String phoneNumber;

    @NotNull
    @Column(name = "enabled")
    private Boolean enabled;

    @Column(name = "create_at")
    private LocalDate createAt;
    @Column(name = "updated_at")
    private LocalDate updateAt;

    public UUID getPhoneId() {
        return phoneId;
    }

    public void setPhoneId(UUID phoneId) {
        this.phoneId = phoneId;
    }

    public @NotBlank UUID getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(@NotBlank UUID idPerson) {
        this.idPerson = idPerson;
    }

    public @NotBlank String getIdPhoneType() {
        return idPhoneType;
    }

    public void setIdPhoneType(@NotBlank String idPhoneType) {
        this.idPhoneType = idPhoneType;
    }

    public @NotBlank String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(@NotBlank String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public @NotNull Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(@NotNull Boolean enabled) {
        this.enabled = enabled;
    }

    public LocalDate getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDate createAt) {
        this.createAt = createAt;
    }

    public LocalDate getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(LocalDate updateAt) {
        this.updateAt = updateAt;
    }
}
