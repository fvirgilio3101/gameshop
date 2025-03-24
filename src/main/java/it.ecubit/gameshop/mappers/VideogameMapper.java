package it.ecubit.gameshop.mappers;

import it.ecubit.gameshop.dto.VideogameDTO;
import it.ecubit.gameshop.entity.Videogame;
import org.mapstruct.Mapper;

@Mapper
public interface VideogameMapper {

    VideogameDTO videogameToVideogameDTO(Videogame videogame);

    Videogame dtoToVideogame(VideogameDTO dto);
}
