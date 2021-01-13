package pl.coderslab.MicroFirm.model;

import org.mindrot.jbcrypt.BCrypt;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length=20)
    @NotBlank
    @Size(max = 20)
    private String loginName;

    @Column(nullable = false, length=50)
    @NotBlank
    @Size(max = 50)
    private String firstName;

    @Column(nullable = false, length=50)
    @NotBlank
    @Size(max = 50)
    private String lastName;

    @Column(nullable = false)
    @NotBlank
    private String password;

    //metoda hashująca hasło:
    public String hashPassword (String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

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
