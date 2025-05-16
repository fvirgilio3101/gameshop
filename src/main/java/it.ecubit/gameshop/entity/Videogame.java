package it.ecubit.gameshop.entity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.Date;
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

    @Column(name="Description",nullable = false, length= 255)
    private String descVideogame;

    @Column(name="Release_Date", nullable = false)
    private Date releaseDateVideogame;

    @ManyToMany( cascade = { CascadeType.ALL },mappedBy = "videogames")
    private List<Order> orders;

    @OneToMany(mappedBy = "videogame", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Rating> ratings;

    @OneToMany(mappedBy = "videogame", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Platform> platforms;

    public Long getIdVideogame() {
        return idVideogame;
    }

    public void setIdVideogame(Long idVideogame) {
        this.idVideogame = idVideogame;
    }

    public String getTitleVideogame() {
        return titleVideogame;
    }

    public void setTitleVideogame(String titleVideogame) {
        this.titleVideogame = titleVideogame;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public Double getPriceVideogame() {
        return priceVideogame;
    }

    public void setPriceVideogame(Double priceVideogame) {
        this.priceVideogame = priceVideogame;
    }

    public String getDescVideogame() {
        return descVideogame;
    }

    public void setDescVideogame(String descVideogame) {
        this.descVideogame = descVideogame;
    }

    public Date getReleaseDateVideogame() {
        return releaseDateVideogame;
    }

    public void setReleaseDateVideogame(Date releaseDateVideogame) {
        this.releaseDateVideogame = releaseDateVideogame;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

    public List<Platform> getPlatforms() {
        return platforms;
    }

    public void setPlatforms(List<Platform> platforms) {
        this.platforms = platforms;
    }

    @Transient
    public Double getAverageRating() {
        if (ratings == null || ratings.isEmpty()) {
            return null; // o 0.0
        }
        return ratings.stream()
                .mapToDouble(Rating::getValue)
                .average()
                .orElse(0.0);
    }


}
