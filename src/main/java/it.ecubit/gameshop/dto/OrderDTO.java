package it.ecubit.gameshop.dto;

import java.util.Date;

public class OrderDTO {
    private Long idOrder;
    private Long idUserOrder;
    private Date dateOrder;
    private Double totalPrice;
    private String addressPrice;

    public Long getIdOrder() {
        return idOrder;
    }
    public void setIdOrder(Long idOrder) {
        this.idOrder = idOrder;
    }
    public Long getIdUserOrder() {
        return idUserOrder;
    }
    public void setIdUserOrder(Long idUserOrder) {
        this.idUserOrder = idUserOrder;
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
