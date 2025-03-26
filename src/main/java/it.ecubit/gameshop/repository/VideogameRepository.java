package it.ecubit.gameshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import it.ecubit.gameshop.entity.Videogame;

import java.util.List;

@Repository
public interface VideogameRepository extends JpaRepository<Videogame, Long> {

    @Query("SELECT v FROM Videogame v JOIN v.genres g WHERE g.name = :genre ORDER BY v.rating DESC")
    List<Videogame> findTopGamesByGenre(@Param("genre") String genre);

}
