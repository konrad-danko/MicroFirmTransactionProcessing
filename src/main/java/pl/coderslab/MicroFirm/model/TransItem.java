package pl.coderslab.MicroFirm.model;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name = "transItems")
public class TransItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @Column(nullable = false)
    @NotNull
    private Transaction transaction;

    @ManyToOne
    @Column(nullable = false)
    @NotNull
    private Product product;

    @Column(nullable = false)
    @NotNull(message = "Pole nie może być puste")
    @Digits(integer=5, fraction=0, message = "Wpisz liczbę całkowitą poniżej 100 000")
    private int quantity;

    @Column(nullable = false, scale=2)
    @NotNull(message = "Pole nie może być puste")
    @Digits(integer=5, fraction=2, message = "Wpisz liczbę poniżej 100 000 z dokładnością do dwóch miejsc dziesiętnych")
    private BigDecimal netPricePer1000; //autoupdated during add operation

    @Column(nullable = false)
    private BigDecimal netAmount; //autoupdated during add operation

    @Column(nullable = false)
    private BigDecimal vatAmount; //autoupdated during add operation

    @Column(nullable = false)
    private BigDecimal grossAmount; //autoupdated during add operation



    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Transaction getTransaction() {
        return transaction;
    }
    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public Product getProduct() {
        return product;
    }
    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getNetPricePer1000() {
        return netPricePer1000;
    }
    public void setNetPricePer1000(BigDecimal netPricePer1000) {
        this.netPricePer1000 = netPricePer1000;
    }

    public BigDecimal getNetAmount() {
        return netAmount;
    }
    public void setNetAmount(BigDecimal netAmount) {
        this.netAmount = netAmount;
    }

    public BigDecimal getVatAmount() {
        return vatAmount;
    }
    public void setVatAmount(BigDecimal vatAmount) {
        this.vatAmount = vatAmount;
    }

    public BigDecimal getGrossAmount() {
        return grossAmount;
    }
    public void setGrossAmount(BigDecimal grossAmount) {
        this.grossAmount = grossAmount;
    }

    
    @Override
    public String toString() {
        return "TransItem{" +
                "id=" + id +
                ", transaction=" + transaction +
                ", product=" + product +
                ", quantity=" + quantity +
                ", netPricePer1000=" + netPricePer1000 +
                ", netAmount=" + netAmount +
                ", vatAmount=" + vatAmount +
                ", grossAmount=" + grossAmount +
                '}';
    }
}
