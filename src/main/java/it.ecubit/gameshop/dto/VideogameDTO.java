package it.ecubit.gameshop.dto;

import java.util.Date;
import java.util.List;

public class VideogameDTO {

    private Long idVideogame;

    private String titleVideogame;

    private List<GenreDTO> genres;

    private Double priceVideogame;

    private String descVideogame;

    private Double rating;

    private Date releaseDateVideogame;

    private String platform;

    private String backgroundImage;

    private Double discount;

    private Integer sales;

    private String coverImage;


    public List<String> getScreenshots() {
        return screenshots;
    }

    public void setScreenshots(List<String> screenshots) {
        this.screenshots = screenshots;
    }

    private List<String> screenshots;

    public String getBackgroundImage() {
        return backgroundImage;
    }

    public void setBackgroundImage(String backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }

    public Date getReleaseDateVideogame() { return releaseDateVideogame; }

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

    public List<GenreDTO> getGenres() {
        return genres;
    }

    public void setReleaseDateVideogame(Date releaseDateVideogame) { this.releaseDateVideogame = releaseDateVideogame; }

    public void setGenres(List<GenreDTO> genres) {
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

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }
}
