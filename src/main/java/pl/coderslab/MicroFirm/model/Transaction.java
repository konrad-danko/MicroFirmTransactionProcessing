package pl.coderslab.MicroFirm.model;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @Column(nullable = false)
    @NotNull(message = "Pole nie może być puste")
    private FirmData firmData;

    @ManyToOne
    @Column(nullable = false)
    @NotNull(message = "Pole nie może być puste")
    private Customer customer;

    private boolean issueInvoice;

    private String invoiceNo;  //autoupdated during add operation

    public enum PaymentType {
        GZ("Gotówka, Zapłacono"),
        GDZ("Gotówka, Do zapłaty"),
        P("Przelew");

        private String description;

        PaymentType(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }

    @Column(nullable = false)
    @NotNull(message = "Wybierz sposób zapłaty")
    private PaymentType paymentType;

    @FutureOrPresent (message = "Data nie może być z przeszłości")
    private LocalDate paymentDueDate;

    private LocalDate transactionDate; //autoupdated during add operation

    @FutureOrPresent (message = "Data nie może być z przeszłości")
    private LocalDate sellDate;

    @Column(nullable = false)
    private BigDecimal totalNetAmount; //autoupdated during add operation

    @Column(nullable = false)
    private BigDecimal totalVatAmount; //autoupdated during add operation

    @Column(nullable = false)
    private BigDecimal totalGrossAmount; //autoupdated during add operation

    @Column(nullable = false)
    private String paymentAmountInWords; //autoupdated during add operation

    @ManyToOne
    private User createdByUser; //autoupdated during add operation

    @ManyToOne
    private User updatedByUser; //autoupdated during edit operation

    private LocalDateTime created; //autoupdated by @PrePersist

    private LocalDateTime updated; //autoupdated by @PreUpdate

    @PrePersist
    public void prePersist() {
        created = LocalDateTime.now();
        transactionDate = LocalDate.now();
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

    public FirmData getFirmData() {
        return firmData;
    }
    public void setFirmData(FirmData firmData) {
        this.firmData = firmData;
    }

    public Customer getCustomer() {
        return customer;
    }
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public boolean isIssueInvoice() {
        return issueInvoice;
    }
    public void setIssueInvoice(boolean issueInvoice) {
        this.issueInvoice = issueInvoice;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }
    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }
    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public LocalDate getPaymentDueDate() {
        return paymentDueDate;
    }
    public void setPaymentDueDate(LocalDate paymentDueDate) {
        this.paymentDueDate = paymentDueDate;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }
    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }

    public LocalDate getSellDate() {
        return sellDate;
    }
    public void setSellDate(LocalDate sellDate) {
        this.sellDate = sellDate;
    }

    public BigDecimal getTotalNetAmount() {
        return totalNetAmount;
    }
    public void setTotalNetAmount(BigDecimal totalNetAmount) {
        this.totalNetAmount = totalNetAmount;
    }

    public BigDecimal getTotalVatAmount() {
        return totalVatAmount;
    }
    public void setTotalVatAmount(BigDecimal totalVatAmount) {
        this.totalVatAmount = totalVatAmount;
    }

    public BigDecimal getTotalGrossAmount() {
        return totalGrossAmount;
    }
    public void setTotalGrossAmount(BigDecimal totalGrossAmount) {
        this.totalGrossAmount = totalGrossAmount;
    }

    public String getPaymentAmountInWords() {
        return paymentAmountInWords;
    }
    public void setPaymentAmountInWords(String paymentAmountInWords) {
        this.paymentAmountInWords = paymentAmountInWords;
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
        return "Transaction{" +
                "id=" + id +
                ", firmData=" + firmData +
                ", customer=" + customer +
                ", issueInvoice=" + issueInvoice +
                ", invoiceNo='" + invoiceNo + '\'' +
                ", paymentType=" + paymentType +
                ", paymentDueDate=" + paymentDueDate +
                ", transactionDate=" + transactionDate +
                ", sellDate=" + sellDate +
                ", totalNetAmount=" + totalNetAmount +
                ", totalVatAmount=" + totalVatAmount +
                ", totalGrossAmount=" + totalGrossAmount +
                ", paymentAmountInWords='" + paymentAmountInWords + '\'' +
                ", createdByUser=" + createdByUser +
                ", updatedByUser=" + updatedByUser +
                ", created=" + created +
                ", updated=" + updated +
                '}';
    }
}
