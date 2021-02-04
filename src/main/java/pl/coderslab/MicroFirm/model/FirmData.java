package pl.coderslab.MicroFirm.model;

import org.hibernate.validator.constraints.pl.NIP;
import org.hibernate.validator.constraints.pl.REGON;
import pl.coderslab.MicroFirm.validator.PostCode;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "firmData")
public class FirmData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "Pole nie może być puste")
    @Size(max = 255, message = "Dopuszczalna ilość znaków wynosi 255")
    private String firmName;

    @Column(nullable = false)
    @PostCode(message = "Wpisz kod pocztowy w formacie XX-XXX")
    private String firmPostCode;

    @Column(nullable = false)
    @NotBlank(message = "Pole nie może być puste")
    @Size(max = 100, message = "Dopuszczalna ilość znaków wynosi 100")
    private String firmCity;

    @Column(nullable = false)
    @NotBlank(message = "Pole nie może być puste")
    @Size(max = 100, message = "Dopuszczalna ilość znaków wynosi 100")
    private String firmStreet;

    @Column(nullable = false)
    @NotBlank(message = "Pole nie może być puste")
    @Size(max = 15, message = "Dopuszczalna ilość znaków wynosi 15")
    private String firmPhone;

    @Column(nullable = false)
    @Size(min=10, max = 10, message = "Wpisz 10 cyfr")
    @NIP(message = "Wpisz prawidłowy nr NIP")
    private String firmNIP;

    @Column(nullable = false)
    @REGON(message = "Wpisz prawidłowy nr REGON")
    private String firmREGON;

    @Column(nullable = false)
    @NotBlank(message = "Pole nie może być puste")
    @Size(max = 50, message = "Dopuszczalna ilość znaków wynosi 50")
    private String firmBankName;

    @Column(nullable = false)
    @Pattern(regexp = "^\\d{2}( \\d{4}){6}$", message = "Wpisz nr w formacie XX XXXX XXXX XXXX XXXX XXXX XXXX")
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
