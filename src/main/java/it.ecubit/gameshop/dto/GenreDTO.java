package it.ecubit.gameshop.dto;


import java.util.List;

public class GenreDTO {
    private long idGenre;
    private String name;
    private List<VideogameDTO> videogames;

    public long getIdGenre() {
        return idGenre;
    }

    public String getName() {
        return name;
    }

    public void setIdGenre(long idGenre) {
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
