package org.alvaro.ferreteria.jose.msvc.products.invoice.entities;

import jakarta.persistence.*;
import org.alvaro.ferreteria.jose.msvc.products.invoice.keys.DevolutionDetailId;

import java.math.BigDecimal;

@Entity
@Table(name = "devolutions_details", schema = "ferreteria")
public class DevolutionDetail {

    @EmbeddedId
    private DevolutionDetailId id;

    @Column(name = "iva", precision = 12, scale = 6, nullable = false)
    private BigDecimal iva;

    @Column(name = "sub_total", precision = 12, scale = 2, nullable = false)
    private BigDecimal subTotal;

    @Column(name = "total", precision = 12, scale = 2, nullable = false)
    private BigDecimal total;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @MapsId("devolutionId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_devolution", nullable = false)
    private Devolution devolution;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_product", nullable = false)
    private Product product;

    public DevolutionDetailId getId() { return id; }
    public void setId(DevolutionDetailId id) { this.id = id; }

    public BigDecimal getIva() { return iva; }
    public void setIva(BigDecimal iva) { this.iva = iva; }

    public BigDecimal getSubTotal() { return subTotal; }
    public void setSubTotal(BigDecimal subTotal) { this.subTotal = subTotal; }

    public BigDecimal getTotal() { return total; }
    public void setTotal(BigDecimal total) { this.total = total; }

    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }

    public Devolution getDevolution() { return devolution; }
    public void setDevolution(Devolution devolution) { this.devolution = devolution; }

    public Product getProduct() { return product; }
    public void setProduct(Product product) { this.product = product; }
}
