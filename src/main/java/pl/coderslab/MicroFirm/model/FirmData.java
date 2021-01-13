package pl.coderslab.MicroFirm.model;

import org.hibernate.validator.constraints.pl.NIP;
import org.hibernate.validator.constraints.pl.REGON;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "firmData")
public class FirmData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank
    @Size(max = 255)
    private String firmName;

    @Column(nullable = false)
    @NotBlank
    @Size(min=6, max = 6)
    private String firmPostCode;

    @Column(nullable = false)
    @NotBlank
    @Size(max = 100)
    private String firmCity;

    @Column(nullable = false)
    @NotBlank
    @Size(max = 100)
    private String firmStreet;

    @Column(nullable = false)
    @NotBlank
    @Size(max = 15)
    private String firmPhone;

    @Column(nullable = false)
    @NotBlank
    @NIP
    private String firmNIP;

    @Column(nullable = false)
    @NotBlank
    @REGON
    private String firmREGON;

    @Column(nullable = false)
    @NotBlank
    @Size(max = 50)
    private String firmBankName;

    @Column(nullable = false)
    @NotBlank
    @Size(min=32, max = 32)
    private String firmBankAccount;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getFirmName() {
        return firmName;
    }
    public void setFirmName(String firmName) {
        this.firmName = firmName;
    }

    public String getFirmPostCode() {
        return firmPostCode;
    }
    public void setFirmPostCode(String firmPostCode) {
        this.firmPostCode = firmPostCode;
    }

    public String getFirmCity() {
        return firmCity;
    }
    public void setFirmCity(String firmCity) {
        this.firmCity = firmCity;
    }

    public String getFirmStreet() {
        return firmStreet;
    }
    public void setFirmStreet(String firmStreet) {
        this.firmStreet = firmStreet;
    }

    public String getFirmPhone() {
        return firmPhone;
    }
    public void setFirmPhone(String firmPhone) {
        this.firmPhone = firmPhone;
    }

    public String getFirmNIP() {
        return firmNIP;
    }
    public void setFirmNIP(String firmNIP) {
        this.firmNIP = firmNIP;
    }

    public String getFirmREGON() {
        return firmREGON;
    }
    public void setFirmREGON(String firmREGON) {
        this.firmREGON = firmREGON;
    }

    public String getFirmBankName() {
        return firmBankName;
    }
    public void setFirmBankName(String firmBankName) {
        this.firmBankName = firmBankName;
    }

    public String getFirmBankAccount() {
        return firmBankAccount;
    }
    public void setFirmBankAccount(String firmBankAccount) {
        this.firmBankAccount = firmBankAccount;
    }

    @Override
    public String toString() {
        return "FirmData{" +
                "id=" + id +
                ", firmName='" + firmName + '\'' +
                ", firmPostCode='" + firmPostCode + '\'' +
                ", firmCity='" + firmCity + '\'' +
                ", firmStreet='" + firmStreet + '\'' +
                ", firmPhone='" + firmPhone + '\'' +
                ", firmNIP='" + firmNIP + '\'' +
                ", firmREGON='" + firmREGON + '\'' +
                ", firmBankName='" + firmBankName + '\'' +
                ", firmBankAccount='" + firmBankAccount + '\'' +
                '}';
    }
}
