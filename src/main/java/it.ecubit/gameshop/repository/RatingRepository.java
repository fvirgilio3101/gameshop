package it.ecubit.gameshop.repository;

import it.ecubit.gameshop.entity.Rating;
import it.ecubit.gameshop.entity.User;
import it.ecubit.gameshop.entity.Videogame;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<Rating,Long> {

    Rating findByVideogameAndUser(Videogame videogame, User user);
}
