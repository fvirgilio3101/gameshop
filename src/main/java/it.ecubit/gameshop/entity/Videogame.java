package it.ecubit.gameshop.entity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="Videogame")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Videogame {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID", nullable = false, length = 18)
    private Long idVideogame;

    @Column(name="Title",nullable = false, length= 50)
    private String titleVideogame;

    @ManyToMany(cascade= CascadeType.ALL)
    @JoinTable(
            name="Videogame_Genre",
            joinColumns = @JoinColumn(name = "Videogame_ID"),
            inverseJoinColumns = @JoinColumn(name = "Genre_ID")
    )
    private List<Genre> genres;

    @Column(name="Price",nullable = false, length= 50)
    private Double priceVideogame;

    @Column(name="Description",nullable = false, length= 50)
    private String descVideogame;

    @ManyToMany( cascade = { CascadeType.ALL },mappedBy = "videogames")
    private List<Order> orders;

    @Column(name="Rating",nullable = false, length= 50)
    private Double rating;

    public Long getIdVideogame() {
        return idVideogame;
    }

    public void setPriceVideogame(Double priceVideogame) {
        this.priceVideogame = priceVideogame;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getTitleVideogame() {
        return titleVideogame;
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

    public void setPriceVideogame(double priceVideogame) {
        this.priceVideogame = priceVideogame;
    }

    public void setDescVideogame(String descVideogame) {
        this.descVideogame = descVideogame;
    }

    public void setIdVideogame(Long idVideogame) {
        this.idVideogame = idVideogame;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }
}
