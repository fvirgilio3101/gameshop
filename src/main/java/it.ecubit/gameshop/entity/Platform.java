package it.ecubit.gameshop.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name="Platforms")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Platform {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID", nullable = false, length = 18)
    private Long idPlatform;

    @Column(name="Name",nullable= false,length = 50 )
    private String name;

    @Column(name = "Abbreviation",nullable = false, length = 50)
    private String abbreviation;

    @ManyToOne
    @JoinColumn(name="Videogame_ID",nullable = false)
    private Videogame videogame;


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

    public Videogame getVideogame() {
        return videogame;
    }

    public void setVideogame(Videogame videogame) {
        this.videogame = videogame;
    }
}
