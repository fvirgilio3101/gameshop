package it.ecubit.gameshop.document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Document(indexName = "videogames")
@JsonIgnoreProperties(ignoreUnknown = true)
public class VideogameDocument {

    @Id
    private Long idVideogame;

    @Field(type = FieldType.Text, analyzer = "standard")
    private String titleVideogame;

    @Field(type=FieldType.Keyword)
    private List<String>genres;

    private Double priceVideogame;

    private String descVideogame;

    private Double rating;

    @Field(type = FieldType.Long)
    private Long releaseDateVideogame;

    @Field(type = FieldType.Text)
    private String platforms;

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

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

    public String getDescVideogame() {
        return descVideogame;
    }

    public void setDescVideogame(String descVideogame) {
        this.descVideogame = descVideogame;
    }

    public Double getPriceVideogame() {
        return priceVideogame;
    }

    public void setPriceVideogame(Double priceVideogame) {
        this.priceVideogame = priceVideogame;
    }

    public Long getReleaseDateVideogame() {
        return releaseDateVideogame;
    }

    public void setReleaseDateVideogame(Long releaseDateVideogame) {
        this.releaseDateVideogame = releaseDateVideogame;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getPlatforms() {
        return platforms;
    }

    public void setPlatforms(String platforms) {
        this.platforms = platforms;
    }
}