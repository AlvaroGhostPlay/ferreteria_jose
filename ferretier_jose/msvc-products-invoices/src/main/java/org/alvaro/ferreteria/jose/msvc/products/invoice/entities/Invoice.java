package org.alvaro.ferreteria.jose.msvc.products.invoice.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "invoices")
public class Invoice {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "invoice_id")
    private UUID invoiceId;

    @NotNull
    @Column(name = "id_person")
    private UUID idClient;

    @NotNull
    @Column(name = "id_employee")
    private UUID idEmplyee;

    @NotNull
    @Column(name = "sub_total")
    private Double subTotal;

    @NotNull
    @Column(name = "iva")
    private Double iva;

    @NotNull
    @Column(name = "total")
    private Double total;

    @Column(name = "state")
    private Boolean state;

    @Column(name = "date")
    private LocalDateTime date;

    @NotBlank
    @Column(name = "id_method_paymment")
    private String idMethodPaymment;

    @NotBlank
    @Column(name = "invoice_number", unique = true)
    private String invoiceNumber;

    @OneToMany(
        mappedBy = "invoice",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private List<InvoiceDetail> details = new ArrayList<>();


    public UUID getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(UUID invoiceId) {
        this.invoiceId = invoiceId;
    }

    public UUID getIdClient() {
        return idClient;
    }

    public void setIdClient(UUID idClient) {
        this.idClient = idClient;
    }

    public Double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(Double subTotal) {
        this.subTotal = subTotal;
    }

    public Double getIva() {
        return iva;
    }

    public void setIva(Double iva) {
        this.iva = iva;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    public UUID getIdEmplyee() {
        return idEmplyee;
    }

    public void setIdEmplyee(UUID idEmplyee) {
        this.idEmplyee = idEmplyee;
    }

    public List<InvoiceDetail> getDetails() {
        return details;
    }

    public void setDetails(List<InvoiceDetail> details) {
        this.details = details;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void addDetail(InvoiceDetail detail) {
        details.add(detail);
        detail.setInvoice(this);
    }

    public void removeDetail(InvoiceDetail detail) {
        details.remove(detail);
        detail.setInvoice(null);
    }

    public String getIdMethodPaymment() {
        return idMethodPaymment;
    }

    public void setIdMethodPaymment(String idMethodPaymment) {
        this.idMethodPaymment = idMethodPaymment;
    }

    public @NotBlank String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(@NotBlank String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }
}
