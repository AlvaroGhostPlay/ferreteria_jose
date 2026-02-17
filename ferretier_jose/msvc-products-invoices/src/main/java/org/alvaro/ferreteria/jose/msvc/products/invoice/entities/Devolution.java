package org.alvaro.ferreteria.jose.msvc.products.invoice.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.*;

@Entity
@Table(name = "devolutions", schema = "ferreteria")
public class Devolution {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "devolution_id")
    private UUID devolutionId;

    @NotBlank
    @Column(name = "invoice_number", nullable = false, length = 20)
    private String invoiceNumber;

    @NotNull
    @Column(name = "id_employee", nullable = false)
    private UUID idEmployee;

    @NotBlank
    @Column(name = "reason", length = 200)
    private String reason;

    @Column(name = "state", nullable = false)
    private Boolean state = true;

    @NotBlank
    @Column(name = "devolution_number", nullable = false, unique = true, length = 20)
    private String devolutionNumber;

    @Temporal(TemporalType.DATE)
    @Column(name = "date")
    private Date date;

    @OneToMany(mappedBy = "devolution", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DevolutionDetail> details = new ArrayList<>();

    public void addDetail(DevolutionDetail detail) {
        details.add(detail);
        detail.setDevolution(this);
    }

    public void removeDetail(DevolutionDetail detail) {
        details.remove(detail);
        detail.setDevolution(null);
    }

    public UUID getDevolutionId() { return devolutionId; }
    public void setDevolutionId(UUID devolutionId) { this.devolutionId = devolutionId; }

    public String getInvoiceNumber() { return invoiceNumber; }
    public void setInvoiceNumber(String invoiceNumber) { this.invoiceNumber = invoiceNumber; }

    public UUID getIdEmployee() { return idEmployee; }
    public void setIdEmployee(UUID idEmployee) { this.idEmployee = idEmployee; }

    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }

    public Boolean getState() { return state; }
    public void setState(Boolean state) { this.state = state; }

    public String getDevolutionNumber() { return devolutionNumber; }
    public void setDevolutionNumber(String devolutionNumber) { this.devolutionNumber = devolutionNumber; }

    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }

    public List<DevolutionDetail> getDetails() { return details; }
    public void setDetails(List<DevolutionDetail> details) { this.details = details; }
}
