package it.ecubit.gameshop.dto;


import java.util.List;

public class GenreDTO {

    private Long idGenre;

    private String name;

    private List<VideogameDTO> videogames;

    public Long getIdGenre() {
        return idGenre;
    }

    public String getName() {
        return name;
    }

    public void setIdGenre(Long idGenre) {
        this.idGenre = idGenre;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<VideogameDTO> getVideogames() {
        return videogames;
    }

    public void setVideogames(List<VideogameDTO> videogames) {
        this.videogames = videogames;
    }

}
