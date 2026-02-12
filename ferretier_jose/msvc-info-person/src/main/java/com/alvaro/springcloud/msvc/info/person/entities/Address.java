package com.alvaro.springcloud.msvc.info.person.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(
        name = "addresses",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_person_address_type",
                        columnNames = {"id_person", "id_address_type"}
                )
        }
)
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "address_id")
    private UUID addressId;

    @Column(name = "id_person")
    @NotNull
    private UUID idPerson;

    @NotBlank
    @Column(name = "id_address_type")
    private String idAddressType;

    @NotNull
    @Column(name = "is_primary")
    private Boolean isPrimary;

    @NotBlank
    @Column(name = "line1")
    private String line1;

    @Column(name = "line2")
    private String line2;

    @NotBlank
    @Column(name = "city")
    private String city;

    @NotBlank
    @Column(name = "state")
    private String state;

    @NotBlank
    @Column(name = "country")
    private String country;

    @NotBlank
    @Column(name = "postal_code")
    private String postalCode;

    @Column(name = "reference")
    private String reference;

    @NotNull
    private Boolean enabled;

    @Column(name = "cerated_at")
    private LocalDate createAt;
    @Column(name = "updated_at")
    private LocalDate updateAt;

    public UUID getAddressId() {
        return addressId;
    }

    public void setAddressId(UUID addressId) {
        this.addressId = addressId;
    }

    public @NotNull UUID getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(@NotNull UUID idPerson) {
        this.idPerson = idPerson;
    }

    public @NotBlank String getIdAddressType() {
        return idAddressType;
    }

    public void setIdAddressType(@NotBlank String idAddressType) {
        this.idAddressType = idAddressType;
    }

    public @NotNull Boolean getPrimary() {
        return isPrimary;
    }

    public void setPrimary(@NotNull Boolean primary) {
        isPrimary = primary;
    }

    public @NotBlank String getLine1() {
        return line1;
    }

    public void setLine1(@NotBlank String line1) {
        this.line1 = line1;
    }

    public String getLine2() {
        return line2;
    }

    public void setLine2(String line2) {
        this.line2 = line2;
    }

    public @NotBlank String getCity() {
        return city;
    }

    public void setCity(@NotBlank String city) {
        this.city = city;
    }

    public @NotBlank String getState() {
        return state;
    }

    public void setState(@NotBlank String state) {
        this.state = state;
    }

    public @NotBlank String getCountry() {
        return country;
    }

    public void setCountry(@NotBlank String country) {
        this.country = country;
    }

    public @NotBlank String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(@NotBlank String postalCode) {
        this.postalCode = postalCode;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
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
