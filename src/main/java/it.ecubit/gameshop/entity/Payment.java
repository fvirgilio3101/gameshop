package it.ecubit.gameshop.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="Payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID", nullable = false, length = 18)
    private Long idPayment;
    @OneToOne
    @JoinColumn(name="ID",insertable = false, updatable = false)
    private Order order;
    @Column(name="Payment_Status", nullable = false)
    private boolean payment_status;
    @Column(name="Payment_Method", nullable = false, length = 50)
    private String payment_method;
    @Column(name="Payment_Date", nullable = false)
    private Date payment_date;

    public Long getIdPayment(){
        return this.idPayment;
    }
    public Order getOrderId(){
        return this.order;
    }
    public boolean getPaymentStatus(){
        return this.payment_status;
    }
    public String getPayment_method(){
        return this.payment_method;
    }
    public Date getPaymentDate(){
        return this.payment_date;
    }

    public void setIdPayment(Long idPayment){
        this.idPayment = idPayment;
    }
    public void setOrder(Order order){
        this.order = order;
    }
    public void setPayment_status(boolean payment_status){
        this.payment_status = payment_status;
    }
    public void setPayment_method(String payment_method){
        this.payment_method = payment_method;
    }
    public void setPayment_date(Date payment_date){
        this.payment_date = payment_date;
    }



}
