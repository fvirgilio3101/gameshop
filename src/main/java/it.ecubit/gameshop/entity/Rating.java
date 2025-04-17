package it.ecubit.gameshop.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Rating", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"videogame_ID", "user_ID"}) // evita doppio voto
})
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID", nullable = false, length = 18)
    private Long id;

    @Column(name = "rating_value", nullable = false)
    private Double value;

    @ManyToOne
    @JoinColumn(name = "videogame_ID", nullable = false)
    private Videogame videogame;

    @ManyToOne
    @JoinColumn(name = "user_ID", nullable = false)
    private User user;


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

    public Videogame getVideogame() {
        return videogame;
    }

    public void setVideogame(Videogame videogame) {
        this.videogame = videogame;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
