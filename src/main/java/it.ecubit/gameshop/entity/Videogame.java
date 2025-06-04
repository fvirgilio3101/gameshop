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

    @ManyToMany(cascade= CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(
            name="Videogame_Genre",
            joinColumns = @JoinColumn(name = "Videogame_ID"),
            inverseJoinColumns = @JoinColumn(name = "Genre_ID")
    )
    private List<Genre> genres;

    @Column(name="Price",nullable = false, length= 50)
    private Double priceVideogame;

    @Column(name="Description",nullable = false, length= 500)
    private String descVideogame;

    @Column(name="Release_Date", nullable = false)
    private Date releaseDateVideogame;

    @ManyToMany( cascade = { CascadeType.ALL },mappedBy = "videogames")
    private List<Order> orders;

    @OneToMany(mappedBy = "videogame", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Rating> ratings;

    @Column(name="Platform")
    private String platform;

    @Column(name="backgroundImage")
    private String backgroundImage;

    @Column(name="sales")
    private Integer sales;

    @Column(name="discount")
    private Double discount;

    @Column(name="Cover_Image")
    private String coverImage;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "Videogame_Screenshots", joinColumns = @JoinColumn(name = "videogame_id"))
    @Column(name = "screenshot_url")
    private List<String> screenshots;

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

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getBackgroundImage() {
        return backgroundImage;
    }

    public void setBackgroundImage(String backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public List<String> getScreenshots() {
        return screenshots;
    }

    public void setScreenshots(List<String> screenshots) {
        this.screenshots = screenshots;
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

    @Transient
    public Double getDiscountedPrice() {
        if (discount == null || discount <= 0 || priceVideogame == null) {
            return priceVideogame;
        }
        return Math.round(priceVideogame * (1 - discount / 100) * 100.0) / 100.0;
    }

}
