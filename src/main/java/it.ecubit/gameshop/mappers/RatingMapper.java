package it.ecubit.gameshop.mappers;

import it.ecubit.gameshop.dto.RatingDTO;

import it.ecubit.gameshop.entity.Rating;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RatingMapper {

    @Mapping(target = "videogameId", source = "videogame.idVideogame")
    @Mapping(target = "userId", source = "user.idUser")
    RatingDTO ratingToRatingDTO(Rating rating);

    @Mapping(target = "videogame", ignore = true) // verranno settati manualmente
    @Mapping(target = "user", ignore = true)
    Rating ratingDTOToRating(RatingDTO dto);
}
