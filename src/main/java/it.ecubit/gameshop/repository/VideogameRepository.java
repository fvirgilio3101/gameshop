package it.ecubit.gameshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import it.ecubit.gameshop.entity.Videogame;
@Repository
public interface VideogameRepository extends JpaRepository<Videogame, Long> {

}
