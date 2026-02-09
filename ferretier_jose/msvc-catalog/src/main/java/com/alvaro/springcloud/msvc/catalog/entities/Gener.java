package com.alvaro.springcloud.msvc.catalog.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "geners")
public class Gener {

    @Id
    @Column(name = "gener_id")
    @NotBlank
    private String generId;

    @NotBlank
    private String gener;

    public String getGenerId() {
        return generId;
    }

    public void setGenerId(String generId) {
        this.generId = generId;
    }

    public String getGener() {
        return gener;
    }

    public void setGener(String gener) {
        this.gener = gener;
    }
}
