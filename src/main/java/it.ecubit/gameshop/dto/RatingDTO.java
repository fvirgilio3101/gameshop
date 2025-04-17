package it.ecubit.gameshop.dto;

public class RatingDTO {

    private Long id;

    private Double value;

    private Long videogameId;

    private Long userId;

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

    public Long getVideogameId() {
        return videogameId;
    }

    public void setVideogameId(Long videogameId) {
        this.videogameId = videogameId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
