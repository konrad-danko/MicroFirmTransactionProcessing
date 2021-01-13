package pl.coderslab.MicroFirm.model;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "Pole nie może być puste")
    @Size(max = 255, message = "Dopuszczalna ilość znaków wynosi 255")
    private String productName;

    @Column(nullable = false, scale=2)
    @NotNull(message = "Pole nie może być puste")
    @Digits(integer=5, fraction=2, message = "Wpisz liczbę poniżej 100 000 z dokładnością do dwóch miejsc dziesiętnych")
    private BigDecimal netPricePer1000;

    @Column(nullable = false, scale=2)
    @NotNull(message = "Pole nie może być puste")
    @Digits(integer=2, fraction=2, message = "Wpisz liczbę poniżej 100 z dokładnością do dwóch miejsc dziesiętnych")
    private BigDecimal vatRate;

    @ManyToOne
    private User createdByUser; //autoupdated during add operation

    @ManyToOne
    private User updatedByUser; //autoupdated during edit operation

    private LocalDateTime created; //autoupdated by @PrePersist

    private LocalDateTime updated; //autoupdated by @PreUpdate

    @PrePersist
    public void prePersist() {
        created = LocalDateTime.now();
    }
    @PreUpdate
    public void preUpdate() {
        updated = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getNetPricePer1000() {
        return netPricePer1000;
    }
    public void setNetPricePer1000(BigDecimal netPricePer1000) {
        this.netPricePer1000 = netPricePer1000;
    }

    public BigDecimal getVatRate() {
        return vatRate;
    }
    public void setVatRate(BigDecimal vatRate) {
        this.vatRate = vatRate;
    }

    public User getCreatedByUser() {
        return createdByUser;
    }
    public void setCreatedByUser(User createdByUser) {
        this.createdByUser = createdByUser;
    }

    public User getUpdatedByUser() {
        return updatedByUser;
    }
    public void setUpdatedByUser(User updatedByUser) {
        this.updatedByUser = updatedByUser;
    }

    public LocalDateTime getCreated() {
        return created;
    }
    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }
    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", netPricePer1000=" + netPricePer1000 +
                ", vatRate=" + vatRate +
                ", createdByUser=" + createdByUser +
                ", updatedByUser=" + updatedByUser +
                ", created=" + created +
                ", updated=" + updated +
                '}';
    }
}
