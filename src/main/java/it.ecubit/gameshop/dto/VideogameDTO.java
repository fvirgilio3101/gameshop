package it.ecubit.gameshop.dto;

import jakarta.persistence.Column;

public class VideogameDTO {

    private Long id;

    private String titleVideogame;

    private String genreVideogame;

    private Double priceVideogame;

    private String descVideogame;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitleVideogame() {
        return titleVideogame;
    }

    public void setTitleVideogame(String titleVideogame) {
        this.titleVideogame = titleVideogame;
    }

    public String getGenreVideogame() {
        return genreVideogame;
    }

    public void setGenreVideogame(String genreVideogame) {
        this.genreVideogame = genreVideogame;
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
}
