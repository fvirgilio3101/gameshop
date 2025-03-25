package it.ecubit.gameshop.service;

import it.ecubit.gameshop.dto.GenreDTO;

import java.util.List;

public interface GenreService {

    List<GenreDTO> readAll();

    GenreDTO read(GenreDTO dto);

    GenreDTO save(GenreDTO dto);

    void delete(GenreDTO dto);

    void deleteAll();
}
