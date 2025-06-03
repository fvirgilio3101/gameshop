package it.ecubit.gameshop.service;

import it.ecubit.gameshop.dto.GenreDTO;
import it.ecubit.gameshop.dto.VideogameDTO;
import it.ecubit.gameshop.entity.Videogame;

import java.util.List;

public interface VideogameService {

    List<VideogameDTO> readAll();

    VideogameDTO read(Long id);

    List<VideogameDTO> readByFilter(VideogameDTO dto);

    VideogameDTO save(VideogameDTO dto);

    VideogameDTO addGenre(List<Long> genreIds,Long id);

    List<VideogameDTO> getTopGamesByGenre(String genre);

    void deleteVideogame(VideogameDTO dtos);

    void deleteAll();

    List<VideogameDTO> getBestSellingGamesFromDb();

    List<Videogame> getVideogameFromDTO(VideogameDTO dto);



}
