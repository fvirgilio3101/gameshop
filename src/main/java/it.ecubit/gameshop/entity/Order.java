package it.ecubit.gameshop.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name="GSOrder")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID",nullable = false,length = 18)
    private Long idOrder;
    @ManyToOne
    @JoinColumn(name="User_ID",nullable = false, insertable = false,updatable = false)
    private User user;
    @Column(name="User_ID",nullable = false)
    private Long idUserOrder;
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name="Order_Videogame",
            joinColumns = @JoinColumn(name = "Order_ID"),
            inverseJoinColumns = @JoinColumn(name = "Videogame_ID")
    )
    private List<Videogame> videogames;
    @Column(name="Order_Date",nullable = false)
    private Date dateOrder;
    @Column(name="Total_price",nullable = false,length = 18)
    private Double totalPrice;
    @Column(name = "Address",nullable = false,length = 50)
    private String addressPrice;

    public Long getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Long idOrder) {
        this.idOrder = idOrder;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getIdUserOrder() {
        return idUserOrder;
    }

    public void setIdUserOrder(Long idUserOrder) {
        this.idUserOrder = idUserOrder;
    }

    public List<Videogame> getVideogames() {
        return videogames;
    }

    public void setVideogames(List<Videogame> videogames) {
        this.videogames = videogames;
    }

    public Date getDateOrder() {
        return dateOrder;
    }

    public void setDateOrder(Date dateOrder) {
        this.dateOrder = dateOrder;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getAddressPrice() {
        return addressPrice;
    }

    public void setAddressPrice(String addressPrice) {
        this.addressPrice = addressPrice;
    }
}
