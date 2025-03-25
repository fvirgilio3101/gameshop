package it.ecubit.gameshop.repository;

import it.ecubit.gameshop.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre,Long> {
}
