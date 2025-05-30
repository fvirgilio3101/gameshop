package it.ecubit.gameshop.mappers;

import it.ecubit.gameshop.dto.VideogameDTO;
import it.ecubit.gameshop.entity.Videogame;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface VideogameMapper {

    @Mappings({
            @Mapping(target = "rating", expression = "java(videogame.getAverageRating())"),
            @Mapping(target = "discountedPrice", expression = "java(videogame.getDiscountedPrice())")
    })
    VideogameDTO videogameToVideogameDTO(Videogame videogame);

    @Mapping(target = "ratings", ignore = true)
    Videogame videogameDTOToVideogame(VideogameDTO dto);
}
