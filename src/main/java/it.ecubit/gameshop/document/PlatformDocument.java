package it.ecubit.gameshop.document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;


@JsonIgnoreProperties(ignoreUnknown = true)
public class PlatformDocument {

    @Id
    private Long idPlatform;

    private String name;

    private String abbreviation;

    public Long getIdPlatform() {
        return idPlatform;
    }

    public void setIdPlatform(Long idPlatform) {
        this.idPlatform = idPlatform;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }
}
