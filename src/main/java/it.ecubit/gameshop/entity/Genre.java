package it.ecubit.gameshop.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="Genre")
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID", nullable = false, length = 18)
    private Long idGenre;

    @Column(name="Title",nullable = false, length= 50)
    private String name;

    @ManyToMany(cascade = CascadeType.REMOVE,mappedBy = "genres")
    private List<Videogame> videogames;

    public Long getIdGenre() {
        return idGenre;
    }

    public void setIdGenre(Long idGenre) {
        this.idGenre = idGenre;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
