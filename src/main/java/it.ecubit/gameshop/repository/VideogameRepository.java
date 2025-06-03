package it.ecubit.gameshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import it.ecubit.gameshop.entity.Videogame;

import java.util.List;

@Repository
public interface VideogameRepository extends JpaRepository<Videogame, Long> {

    @Query(
            value = """
        SELECT v.* 
        FROM Videogame v
        JOIN Videogame_Genre vg ON v.id = vg.Videogame_ID
        JOIN Genre g ON vg.Genre_ID = g.id
        JOIN Rating r ON r.videogame_id = v.id
        WHERE g.name = :genre
        GROUP BY v.id
        ORDER BY AVG(r.value) DESC
    """,
            nativeQuery = true
    )
    List<Videogame> findTopGamesByGenre(@Param("genre") String genre);

    List<Videogame> findByDiscountGreaterThan(Double discount);

    List<Videogame> findBySalesGreaterThan(Integer sales);


}
