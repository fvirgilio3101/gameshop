package it.ecubit.gameshop.document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.time.LocalDate;
import java.util.Date;

@Document(indexName = "videogames")
@JsonIgnoreProperties(ignoreUnknown = true)
public class VideogameDocument {

    @Id
    private String idVideogame;

    private String titleVideogame;
    private String descriptionVideogame;
    private Double priceVideogame;
    private Double rating;
    private Date releaseDateVideogame;

    public String getIdVideogame() {
        return idVideogame;
    }

    public void setIdVideogame(String idVideogame) {
        this.idVideogame = idVideogame;
    }

    public String getTitleVideogame() {
        return titleVideogame;
    }

    public void setTitleVideogame(String titleVideogame) {
        this.titleVideogame = titleVideogame;
    }

    public String getDescriptionVideogame() {
        return descriptionVideogame;
    }

    public void setDescriptionVideogame(String descriptionVideogame) {
        this.descriptionVideogame = descriptionVideogame;
    }

    public Double getPriceVideogame() {
        return priceVideogame;
    }

    public void setPriceVideogame(Double priceVideogame) {
        this.priceVideogame = priceVideogame;
    }



    public Date getReleaseDateVideogame() {
        return releaseDateVideogame;
    }

    public void setReleaseDateVideogame(Date releaseDateVideogame) {
        this.releaseDateVideogame = releaseDateVideogame;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }
}

