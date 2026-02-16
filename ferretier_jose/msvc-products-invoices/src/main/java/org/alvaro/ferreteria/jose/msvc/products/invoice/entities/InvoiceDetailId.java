package org.alvaro.ferreteria.jose.msvc.products.invoice.entities;

import java.io.Serializable;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Embeddable
public class InvoiceDetailId implements Serializable {

    @Column(name = "id_invoice", nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID invoiceId;

    @Column(name = "correlative", nullable = false)
    private Integer correlative;

    public UUID getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(UUID invoiceId) {
        this.invoiceId = invoiceId;
    }

    public Integer getCorrelative() {
        return correlative;
    }

    public void setCorrelative(Integer correlative) {
        this.correlative = correlative;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((invoiceId == null) ? 0 : invoiceId.hashCode());
        result = prime * result + ((correlative == null) ? 0 : correlative.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        InvoiceDetailId other = (InvoiceDetailId) obj;
        if (invoiceId == null) {
            if (other.invoiceId != null)
                return false;
        } else if (!invoiceId.equals(other.invoiceId))
            return false;
        if (correlative == null) {
            if (other.correlative != null)
                return false;
        } else if (!correlative.equals(other.correlative))
            return false;
        return true;
    }
}
