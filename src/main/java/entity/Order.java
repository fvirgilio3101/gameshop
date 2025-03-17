package entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="Order")
public class Order {
    @Id
    @GeneratedValue
    @Column(name="ID",nullable = false,length = 18)
    private Long idOrder;
    @OneToMany
    @JoinColumn(name="User_ID",nullable = false,insertable = false,updatable = false)
    private Long idUserOrder;
    @OneToMany
    @JoinColumn(name="Videogame_ID",nullable = false,insertable = false,updatable = false)
    private long idVideogameOrder;
    @Column(name="Order_Date",nullable = false)
    private Date dateOrder;
    @Column(name="Total_price",nullable = false,length = 18)
    private Double totalPrice;
    @Column(name = "Address",nullable = false,length = 50)
    private String addressPrice;

    public Long getIdOrder() {
        return idOrder;
    }

    public Long getIdUserOrder() {
        return idUserOrder;
    }

    public long getIdVideogameOrder() {
        return idVideogameOrder;
    }

    public Date getDateOrder() {
        return dateOrder;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public String getAddressPrice() {
        return addressPrice;
    }

    public void setIdOrder(Long idOrder) {
        this.idOrder = idOrder;
    }

    public void setIdUserOrder(Long idUserOrder) {
        this.idUserOrder = idUserOrder;
    }

    public void setIdVideogameOrder(long idVideogameOrder) {
        this.idVideogameOrder = idVideogameOrder;
    }

    public void setDateOrder(Date dateOrder) {
        this.dateOrder = dateOrder;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setAddressPrice(String addressPrice) {
        this.addressPrice = addressPrice;
    }
}
