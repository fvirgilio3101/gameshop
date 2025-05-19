package it.ecubit.gameshop.dto;

import it.ecubit.gameshop.entity.Platform;

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

    private List<PlatformDTO> platforms;

    private String coverImage;


    public List<String> getScreenshots() {
        return screenshots;
    }

    public void setScreenshots(List<String> screenshots) {
        this.screenshots = screenshots;
    }

    private List<String> screenshots;


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

    public List<PlatformDTO> getPlatforms() {
        return platforms;
    }

    public void setPlatforms(List<PlatformDTO> platforms) {
        this.platforms = platforms;
    }


    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }
}
