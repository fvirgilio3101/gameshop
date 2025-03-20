package it.ecubit.gameshop.service;

import it.ecubit.gameshop.entity.Videogame;

import java.util.List;

public interface VideogameService {

    List<Videogame> readAll();

    Videogame read(Videogame videogame);

    List<Videogame> readByFilter(Videogame videogame);

    Videogame save(Videogame videogame);

    void deleteVideogame(Videogame videogame);

    void deleteAll();


}
