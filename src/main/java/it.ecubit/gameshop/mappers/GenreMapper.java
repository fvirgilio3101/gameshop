package it.ecubit.gameshop.mappers;

import it.ecubit.gameshop.dto.GenreDTO;
import it.ecubit.gameshop.entity.Genre;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GenreMapper {

    GenreDTO genreToGenreDTO (Genre genre);
    Genre genreDTOtoGenre (GenreDTO dto);
}
