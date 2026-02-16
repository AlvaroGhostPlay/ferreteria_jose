package com.alvaro.springcloud.msvc.catalog.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "brands")
public class Brand {

    @Id
    @Column(name = "brand_id")
    @NotBlank
    private String brandId;

    @NotBlank
    @Column(name = "brand")
    private String brand;

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
    
    
}