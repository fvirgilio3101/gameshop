package it.ecubit.gameshop.entity;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="Videogame")
public class Videogame {
    @Id
    @GeneratedValue
    @Column(name="ID", nullable = false, length = 18)
    private Long idVideogame;

    @Column(name="Title",nullable = false, length= 50)
    private String titleVideogame;

    @Column(name="Genre",nullable = false, length= 50)
    private String genreVideogame;

    @Column(name="Price",nullable = false, length= 50)
    private Double priceVideogame;

    @Column(name="Description",nullable = false, length= 50)
    private String descVideogame;

    @ManyToMany(mappedBy = "videogames")
    private List<Order> orders;

    public Long getIdVideogame() {
        return idVideogame;
    }

    public String getTitleVideogame() {
        return titleVideogame;
    }

    public String getGenreVideogame() {
        return genreVideogame;
    }

    public Double getPriceVideogame() {
        return priceVideogame;
    }

    public String getDescVideogame() {
        return descVideogame;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public void setIdVideogame(long idVideogame) {
        this.idVideogame = idVideogame;
    }

    public void setTitleVideogame(String titleVideogame) {
        this.titleVideogame = titleVideogame;
    }

    public void setGenreVideogame(String genreVideogame) {
        this.genreVideogame = genreVideogame;
    }

    public void setPriceVideogame(double priceVideogame) {
        this.priceVideogame = priceVideogame;
    }

    public void setDescVideogame(String descVideogame) {
        this.descVideogame = descVideogame;
    }
}
