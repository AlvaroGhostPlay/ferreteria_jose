package org.alvaro.ferreteria.jose.msvc.products.invoice.keys;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Embeddable
public class DevolutionDetailId implements Serializable {

    @Column(name = "id_devolution", nullable = false)
    private UUID devolutionId;

    @Column(name = "correlative", nullable = false)
    private Integer correlative;

    public UUID getDevolutionId() { return devolutionId; }
    public void setDevolutionId(UUID devolutionId) { this.devolutionId = devolutionId; }

    public Integer getCorrelative() { return correlative; }
    public void setCorrelative(Integer correlative) { this.correlative = correlative; }

    @Override public int hashCode() { return java.util.Objects.hash(devolutionId, correlative); }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof DevolutionDetailId other)) return false;
        return java.util.Objects.equals(devolutionId, other.devolutionId)
                && java.util.Objects.equals(correlative, other.correlative);
    }
}

