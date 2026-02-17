package org.alvaro.ferreteria.jose.msvc.products.invoice.entities;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import org.alvaro.ferreteria.jose.msvc.products.invoice.keys.InvoiceDetailId;

@Entity
@Table(name = "invoices_details")
public class InvoiceDetail {
    @EmbeddedId
    private InvoiceDetailId id;

    //@Column(name = "id_product", nullable = false)
    //private UUID productId;

    @Column(name = "iva", precision = 12, scale = 6, nullable = false)
    private BigDecimal iva;

    @Column(name = "sub_total", precision = 12, scale = 2, nullable = false)
    private BigDecimal subTotal;

    @Column(name = "total", precision = 12, scale = 2, nullable = false)
    private BigDecimal total;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    // Relación SOLO con Invoice (si esa entidad existe en este MS)
    @MapsId("invoiceId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_invoice", nullable = false)
    private Invoice invoice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_product", nullable = false)
    private Product product;
    
    public InvoiceDetailId getId() {
        return id;
    }

    public void setId(InvoiceDetailId id) {
        this.id = id;
    }



    public BigDecimal getIva() {
        return iva;
    }

    public void setIva(BigDecimal iva) {
        this.iva = iva;
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
