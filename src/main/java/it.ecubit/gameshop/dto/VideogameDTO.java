package it.ecubit.gameshop.dto;

import java.util.List;

public class VideogameDTO {

    private Long idVideogame;

    private String titleVideogame;

    private List<GenreDTO> genres;

    private Double priceVideogame;

    private String descVideogame;

    private List<RatingDTO> ratings;


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

    public List<RatingDTO> getRatings() {
        return ratings;
    }

    public void setRatings(List<RatingDTO> rating) {
        this.ratings = rating;
    }

    public Double getAverageRating() {
        if (ratings == null || ratings.isEmpty()) return 0.0;
        return ratings.stream().mapToDouble(RatingDTO::getValue).average().orElse(0.0);
    }
}
