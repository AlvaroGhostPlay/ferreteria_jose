package com.alvaro.springcloud.msvc.catalog.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "methods_paymments")
public class MethodPaymment {

    @Id
    @Column(name = "method_paymment_id")
    @NotBlank
    private String methodPaymmentId;

    @NotBlank
    @Column(name = "method_paymment")
    private String methodPaymment;

    public String getMethodPaymmentId() {
        return methodPaymmentId;
    }

    public void setMethodPaymmentId(String methodPaymmentId) {
        this.methodPaymmentId = methodPaymmentId;
    }

    public String getMethodPaymment() {
        return methodPaymment;
    }

    public void setMethodPaymment(String methodPaymment) {
        this.methodPaymment = methodPaymment;
    }

    
}