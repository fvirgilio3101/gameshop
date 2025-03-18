package entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID", nullable = false, length = 18)
    private Long idUser;
    @Column(name="Name", nullable = false, length = 50)
    private String name;
    @Column(name="Surname", nullable = false, length = 50)
    private String surname;
    @Column(name="Email", nullable = false, length = 50)
    private String email;
    @Column(name="Username", nullable = false, length = 50)
    private String username;
    @Column(name="Password", nullable = false, length = 8)
    private String password;
    @Column(name="Address", nullable = false, length = 50)
    private String address;
    @Column(name="Phone_Number", nullable = false, length = 50)
    private String phone_number;

    public Long getIdUser() {
        return idUser;
    }
    public String getName() {
        return name;
    }
    public String getSurname() {
        return surname;
    }
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
    public String getUsername() {
        return username;
    }
    public String getAddress() {
        return address;
    }
    public String getPhone_number() {
        return phone_number;
    }
    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }




}
