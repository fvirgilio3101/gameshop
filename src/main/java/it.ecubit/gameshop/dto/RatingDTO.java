package it.ecubit.gameshop.dto;

public class RatingDTO {

    private Long id;

    private Double value;

    private VideogameDTO videogame;

    private UserDTO user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public VideogameDTO getVideogame() {
        return videogame;
    }

    public void setVideogame(VideogameDTO videogame) {
        this.videogame = videogame;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }
}
