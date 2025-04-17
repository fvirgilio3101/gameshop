package it.ecubit.gameshop.mappers;

import it.ecubit.gameshop.dto.VideogameDTO;
import it.ecubit.gameshop.entity.Videogame;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface VideogameMapper {

    @Mapping(target = "rating", expression = "java(videogame.getAverageRating())")
    VideogameDTO videogameToVideogameDTO(Videogame videogame);

    @Mapping(target = "ratings", ignore = true)
    Videogame videogameDTOToVideogame(VideogameDTO dto);
}
