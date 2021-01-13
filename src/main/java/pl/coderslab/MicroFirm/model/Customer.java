package pl.coderslab.MicroFirm.model;

import org.hibernate.validator.constraints.pl.NIP;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "Pole nie może być puste")
    @Size(max = 255, message = "Dopuszczalna ilość znaków wynosi 255")
    private String customerName;

    @Column(nullable = false)
    @Size(min=6, max = 6, message = "Wpisz kod pocztowy w formacie XX-XXX")
    private String customerPostCode;

    @Column(nullable = false)
    @NotBlank(message = "Pole nie może być puste")
    @Size(max = 100, message = "Dopuszczalna ilość znaków wynosi 100")
    private String customerCity;

    @Column(nullable = false)
    @NotBlank(message = "Pole nie może być puste")
    @Size(max = 100, message = "Dopuszczalna ilość znaków wynosi 100")
    private String customerStreet;

    @Column(unique = true, nullable = false)
    @NIP(message = "Wpisz prawidłowy nr NIP")
    private String customerNIP;

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

    public String getCustomerName() {
        return customerName;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPostCode() {
        return customerPostCode;
    }
    public void setCustomerPostCode(String customerPostCode) {
        this.customerPostCode = customerPostCode;
    }

    public String getCustomerCity() {
        return customerCity;
    }
    public void setCustomerCity(String customerCity) {
        this.customerCity = customerCity;
    }

    public String getCustomerStreet() {
        return customerStreet;
    }
    public void setCustomerStreet(String customerStreet) {
        this.customerStreet = customerStreet;
    }

    public String getCustomerNIP() {
        return customerNIP;
    }
    public void setCustomerNIP(String customerNIP) {
        this.customerNIP = customerNIP;
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
        return "Customer{" +
                "id=" + id +
                ", customerName='" + customerName + '\'' +
                ", customerPostCode='" + customerPostCode + '\'' +
                ", customerCity='" + customerCity + '\'' +
                ", customerStreet='" + customerStreet + '\'' +
                ", customerNIP='" + customerNIP + '\'' +
                ", createdByUser=" + createdByUser +
                ", updatedByUser=" + updatedByUser +
                ", created=" + created +
                ", updated=" + updated +
                '}';
    }
}
