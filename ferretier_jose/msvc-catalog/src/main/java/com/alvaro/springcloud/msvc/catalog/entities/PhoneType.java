package com.alvaro.springcloud.msvc.catalog.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "phones_types")
public class PhoneType {

    @Id
    @Column(name = "phone_type_id")
    @NotBlank
    private String phoneTypeId;

    @Column(name = "phone_type")
    @NotBlank
    private String phoneType;

    public String getPhoneTypeId() {
        return phoneTypeId;
    }

    public void setPhoneTypeId(String phoneTypeId) {
        this.phoneTypeId = phoneTypeId;
    }

    public String getPhoneType() {
        return phoneType;
    }

    public void setPhoneType(String phoneType) {
        this.phoneType = phoneType;
    }
}
