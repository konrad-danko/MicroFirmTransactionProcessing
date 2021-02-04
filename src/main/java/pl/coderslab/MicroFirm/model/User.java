package pl.coderslab.MicroFirm.model;

import pl.coderslab.MicroFirm.validator.UniqueUser;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "Pole nie może być puste")
    @Size(max = 20, message = "Dopuszczalna ilość znaków wynosi 20")
    @UniqueUser(message = "Ten 'LoginName' jest już przypisany do innego użytkownika")
    private String loginName;

    @Column(nullable = false)
    @NotBlank(message = "Pole nie może być puste")
    @Size(max = 50, message = "Dopuszczalna ilość znaków wynosi 50")
    private String firstName;

    @Column(nullable = false)
    @NotBlank(message = "Pole nie może być puste")
    @Size(max = 50, message = "Dopuszczalna ilość znaków wynosi 50")
    private String lastName;

    @Column(nullable = false)
    @NotBlank(message = "Pole nie może być puste")
    private String password;
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }
    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", loginName='" + loginName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
    
}
