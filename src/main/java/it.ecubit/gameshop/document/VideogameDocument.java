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

    private String descVideogame;

    private Double priceVideogame;

    private Double rating;

    @Field(type = FieldType.Long)
    private Long releaseDateVideogame;

    // CAMBIATO: Da List<PlatformDocument> a List<String>
    @Field(type = FieldType.Keyword)  // Use Keyword for exact matches
    private List<String> platforms;

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

    public List<String> getPlatforms() {
        return platforms;
    }

    public void setPlatforms(List<String> platforms) {
        this.platforms = platforms;
    }
}